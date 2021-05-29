package com.lol.scout.facade;

import com.lol.scout.domain.cache.RankCache;
import com.lol.scout.domain.currentgame.CurrentGameInfo;
import com.lol.scout.domain.league.LeagueEntry;
import com.lol.scout.domain.summoner.Summoner;
import com.lol.scout.manager.SummonerCacheManager;
import com.lol.scout.mapper.SummonerCacheMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Component
@RequiredArgsConstructor
public class SummonerFacade {

    private final SummonerCacheManager summonerCacheManager;
    private final SummonerCacheMapper summonerCacheMapper;

    public Optional<CurrentGameInfo> getLiveGame(String server, String summonerId) {
        return summonerCacheManager.fetchCurrentGameFromApi(server.toUpperCase(),summonerId);
    }

    public Optional<Summoner> getSummonerByName(String server, String name) {
        List<Summoner> list =  summonerCacheManager.getSummonerFromCacheByName(name);
        if (list.size()>0) {
            Optional<Summoner> optional = list.stream().filter(e->e.getRegion().equalsIgnoreCase(server)).findFirst();
            if (optional.isPresent()) {
                boolean valid = LocalDateTime.ofInstant(Instant.ofEpochMilli(optional.get().getLastUpdate()),
                        ZoneId.systemDefault())
                        .plusHours(3)
                        .isAfter(LocalDateTime.now());
                if (valid) return optional;
                else return summonerCacheManager.fetchSummonerFromTheApiByPuuid(server.toUpperCase(),optional.get().getPuuid());
            }
        }
        return summonerCacheManager.fetchSummonerFromTheApiByName(server.toUpperCase(),name);

    }

    public Optional<Summoner> getSummonerByPuuid(String puuid) {
        return summonerCacheManager.getSummonerFromCacheByPuuid(puuid);
    }

    public Set<LeagueEntry> getRankEntries(String server, String summonerId) {
        Optional<RankCache> optional = summonerCacheManager.getRankEntriesFromCache(summonerId);
        if (optional.isPresent()) {
            RankCache cache = optional.get();
            boolean valid = LocalDateTime.ofInstant(Instant.ofEpochMilli(cache.getLastUpdate()),
                    ZoneId.systemDefault())
                    .plusMinutes(30)
                    .isAfter(LocalDateTime.now());
            if (valid) return summonerCacheMapper.mapRankCacheToLeagueEntry(cache);
        }
        optional = summonerCacheManager.fetchRankEntriesFromApi(server.toUpperCase(),summonerId);
        if (optional.isPresent()) return summonerCacheMapper.mapRankCacheToLeagueEntry(optional.get());
        return Collections.emptySet();
    }

}
