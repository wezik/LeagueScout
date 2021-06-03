package com.lol.scout.manager;

import com.lol.scout.domain.cache.ChampionsCache;
import com.lol.scout.domain.champion.ChampionListDto;
import com.lol.scout.facade.DataFacade;
import com.lol.scout.mapper.ChampionsCacheMapper;
import com.lol.scout.service.api.ApiChampionService;
import com.lol.scout.service.cache.ChampionsCacheService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChampionCacheManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChampionCacheManager.class);

    private final ChampionsCacheService championsCacheService;
    private final ChampionsCacheMapper championsCacheMapper;

    private final ApiChampionService apiChampionService;

    private final DataFacade dataFacade;

    public Optional<ChampionsCache> getChampionsFromCache(String patch) {
        return championsCacheService.get(patch);
    }

    public Optional<ChampionsCache> cacheChampionsFromApi(String patch) {
        if (!dataFacade.getVersions().contains(patch)) return Optional.empty();
        Optional<ChampionListDto> apiResponse = apiChampionService.getChampionListDto(patch);
        if (apiResponse.isEmpty()) return Optional.empty();
        ChampionsCache cache = championsCacheMapper.mapChampionListDtoToChampionsCache(apiResponse.get(),apiResponse.get().getVersion());
        ChampionsCache result = championsCacheService.save(cache);
        logCaching();
        return Optional.of(result);
    }

    private void logCaching() {
        LOGGER.info("Data was cached");
    }

}
