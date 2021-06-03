package com.lol.scout.facade;

import com.lol.scout.config.CoreConfig;
import com.lol.scout.domain.cache.LanguagesCache;
import com.lol.scout.domain.cache.SummonerSpellCache;
import com.lol.scout.domain.cache.VersionsCache;
import com.lol.scout.domain.queues.QueueDto;
import com.lol.scout.domain.summoners.SummonerSpellsDto;
import com.lol.scout.manager.DataCacheManager;
import com.lol.scout.mapper.DataCacheMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataFacade {

    private final CoreConfig coreConfig;

    private final DataCacheManager dataCacheManager;
    private final DataCacheMapper dataCacheMapper;

    public List<String> getVersions() {
        Optional<VersionsCache> optional = dataCacheManager.getVersionsFromCache();
        if (optional.isPresent()) {
            VersionsCache cache = optional.get();
            LocalDateTime lastUpdate = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(cache.getLastUpdate()),ZoneId.systemDefault());
            if (!lastUpdate.isBefore(LocalDateTime.now().minusHours(coreConfig.getUpdateTimeVersions()))) {
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
            if (!cache.getLastUpdate().isBefore(LocalDate.now().minusDays(coreConfig.getUpdateTimeLocales()))) {
                return dataCacheMapper.mapLanguagesCacheToList(cache);
            }
        }
        optional = dataCacheManager.cacheLanguagesFromApi();
        if (optional.isPresent()) {
            return dataCacheMapper.mapLanguagesCacheToList(optional.get());
        }
        return Collections.emptyList();
    }

    public Optional<SummonerSpellsDto> getSummonerSpells() {
        Optional<SummonerSpellCache> optional = dataCacheManager.getSummonerSpellsFromCache();
        if (optional.isPresent()) {
            SummonerSpellCache cache = optional.get();
            LocalDateTime lastUpdate = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(cache.getLastUpdate()),ZoneId.systemDefault());
            if (!lastUpdate.isBefore(LocalDateTime.now().minusDays(coreConfig.getUpdateTimeSummonerSpells()))) {
                return Optional.ofNullable(dataCacheMapper.maSummonerSpellCacheToSummonerSpellsDto(cache));
            }
        }
        optional = dataCacheManager.cacheSummonerSpellsFromApi();
        return optional.map(dataCacheMapper::maSummonerSpellCacheToSummonerSpellsDto);
    }

}
