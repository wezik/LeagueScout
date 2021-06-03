package com.lol.scout.facade;

import com.lol.scout.api.league.config.LeagueApiConfig;
import com.lol.scout.config.CoreConfig;
import com.lol.scout.domain.cache.ChampionsCache;
import com.lol.scout.domain.champion.ChampionDetailsDto;
import com.lol.scout.domain.champion.ChampionListDto;
import com.lol.scout.manager.ChampionCacheManager;
import com.lol.scout.mapper.ChampionsCacheMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChampionFacade {

    private final ChampionCacheManager championCacheManager;
    private final ChampionsCacheMapper championsCacheMapper;
    private final LeagueApiConfig leagueApiConfig;

    public Optional<ChampionListDto> getChampions(String patch) {
        Optional<ChampionsCache> optional = championCacheManager.getChampionsFromCache(patch);
        if (optional.isPresent()) {
            ChampionsCache cache = optional.get();
            if (cache.getPatch().equalsIgnoreCase(leagueApiConfig.getVersion())) {
                return Optional.ofNullable(championsCacheMapper.mapChampionsCacheToChampionListDto(cache));
            }
        }
        optional = championCacheManager.cacheChampionsFromApi(patch);
        return optional.map(championsCacheMapper::mapChampionsCacheToChampionListDto);
    }

    public Optional<ChampionDetailsDto> getChampionDetails(String champion, String patch) {
        return Optional.empty(); //TODO
    }

}
