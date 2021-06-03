package com.lol.scout.manager;

import com.lol.scout.domain.cache.RankCache;
import com.lol.scout.domain.currentgame.CurrentGameInfo;
import com.lol.scout.domain.league.LeagueEntry;
import com.lol.scout.domain.summoner.Summoner;
import com.lol.scout.mapper.SummonerCacheMapper;
import com.lol.scout.service.*;
import com.lol.scout.service.api.ApiSummonerService;
import com.lol.scout.service.cache.RankCacheService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SummonerCacheManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(SummonerCacheManager.class);

    private final SummonersService summonersService;
    private final RankCacheService rankCacheService;

    private final SummonerCacheMapper summonerCacheMapper;

    private final ApiSummonerService apiSummonerService;

    public List<Summoner> getSummonerFromCacheByName(String name) {
        return summonersService.getByName(name);
    }

    public Optional<Summoner> fetchSummonerFromTheApiByName(String server, String name) {
        Optional<Summoner> optional = apiSummonerService.getSummonerByName(server,name);
        return optional.map(summoner -> cacheSummoner(summoner, server));
    }

    public Optional<Summoner> getSummonerFromCacheByPuuid(String puuid) {
        return summonersService.getByPuuid(puuid);
    }

    public Optional<Summoner> fetchSummonerFromTheApiByPuuid(String server, String puuid) {
        Optional<Summoner> optional = apiSummonerService.getSummonerByPuuid(server,puuid);
        return optional.map(summoner -> cacheSummoner(summoner, server));
    }

    public Optional<RankCache> getRankEntriesFromCache(String summonerId) {
        return rankCacheService.getBySummonerId(summonerId);
    }

    public Optional<RankCache> fetchRankEntriesFromApi(String server, String summonerId) {
        Set<LeagueEntry> set = apiSummonerService.getRankEntries(server,summonerId);
        if (!set.isEmpty()) {
            RankCache cache = summonerCacheMapper.mapLeagueEntryToRankCache(set,server,summonerId,System.currentTimeMillis());
            logCaching();
            return Optional.ofNullable(rankCacheService.save(cache));
        }
        return Optional.empty();
    }

    public Optional<CurrentGameInfo> fetchCurrentGameFromApi(String server, String summonerId) {
        return apiSummonerService.getCurrentGameById(server,summonerId);
    }

    private Summoner cacheSummoner(Summoner summoner, String server) {
        summoner.setRegion(server);
        summoner.setLastUpdate(System.currentTimeMillis());
        logCaching();
        return summonersService.save(summoner);
    }

    private void logCaching() {
        LOGGER.info("Data was cached");
    }

}
