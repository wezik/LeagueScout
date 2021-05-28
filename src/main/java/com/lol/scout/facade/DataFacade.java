package com.lol.scout.facade;

import com.lol.scout.domain.cache.LanguagesCache;
import com.lol.scout.domain.cache.VersionsCache;
import com.lol.scout.domain.champion.ChampionListDto;
import com.lol.scout.exception.ApiFetchFailedException;
import com.lol.scout.manager.DataCacheManager;
import com.lol.scout.mapper.DataCacheMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataFacade {

    private final DataCacheManager dataCacheManager;
    private final DataCacheMapper dataCacheMapper;

    public List<String> getVersions() {
        Optional<VersionsCache> optional = dataCacheManager.getVersionsFromCache();
        if (optional.isPresent()) {
            VersionsCache cache = optional.get();
            if (!cache.getLastUpdate().isBefore(LocalDate.now())) {
                return dataCacheMapper.mapVersionsCacheToList(cache);
            }
        }
        optional = dataCacheManager.cacheVersionsFromApi();
        if (optional.isPresent()) {
            return dataCacheMapper.mapVersionsCacheToList(optional.get());
        }
        return Collections.emptyList();
    }

    public List<String> getLanguages() {
        Optional<LanguagesCache> optional = dataCacheManager.getLanguagesFromCache();
        if (optional.isPresent()) {
            LanguagesCache cache = optional.get();
            if (!cache.getLastUpdate().isBefore(LocalDate.now().minusDays(6))) {
                return dataCacheMapper.mapLanguagesCacheToList(cache);
            }
        }
        optional = dataCacheManager.cacheLanguagesFromApi();
        if (optional.isPresent()) {
            return dataCacheMapper.mapLanguagesCacheToList(optional.get());
        }
        return Collections.emptyList();
    }

}
