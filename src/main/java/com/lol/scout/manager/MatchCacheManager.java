package com.lol.scout.manager;

import com.google.gson.GsonBuilder;
import com.lol.scout.domain.cache.ChampionsCache;
import com.lol.scout.domain.cache.MatchCache;
import com.lol.scout.domain.champion.ChampionListDto;
import com.lol.scout.domain.match.MatchDto;
import com.lol.scout.domain.match.ParticipantDto;
import com.lol.scout.domain.summoner.ChampionStats;
import com.lol.scout.mapper.MatchCacheMapper;
import com.lol.scout.service.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MatchCacheManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchCacheManager.class);

    private final MatchesCacheService matchesCacheService;
    private final MatchCacheMapper matchCacheMapper;

    private final ChampionStatsService championStatsService;

    private final ApiMatchService apiMatchService;

    public Optional<MatchCache> getMatchDetailsFromCache(String matchIdWithServerId) {
        return matchesCacheService.getById(matchIdWithServerId);
    }

    public Optional<MatchCache> cacheMatchDetailsFromApi(String server, String matchIdWithServerId) {
        Optional<MatchDto> apiResponse = apiMatchService.getMatchData(server,matchIdWithServerId);
        if (apiResponse.isEmpty()) return Optional.empty();
        MatchDto matchDto = apiResponse.get();
        MatchCache cache = matchCacheMapper.mapMatchDtoToMatchCache(matchDto);
        MatchCache result = matchesCacheService.save(cache);
        saveChampionStatsFromGame(matchDto);
        logCaching();
        return Optional.of(result);
    }

    private void saveChampionStatsFromGame(MatchDto matchDto) {
        for (ParticipantDto participant : matchDto.getInfo().getParticipants()) {
            ChampionStats championStats = new ChampionStats(
                    0L,
                    matchDto.getMetaData().getMatchId(),
                    participant.getPuuid(),
                    matchDto.getInfo().getQueueId(),
                    participant.getChampionId(),
                    participant.isWin(),
                    participant.getKills(),
                    participant.getDeaths(),
                    participant.getAssists()
            );
            championStatsService.save(championStats);
        }
    }

    private void logCaching() {
        LOGGER.info("Data was cached");
    }

}
