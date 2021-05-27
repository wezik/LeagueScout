package com.lol.scout.facade;

import com.google.gson.GsonBuilder;
import com.lol.scout.domain.ChampionListDto;
import com.lol.scout.domain.cache.ChampionsCache;
import com.lol.scout.domain.cache.LanguagesCache;
import com.lol.scout.domain.cache.VersionsCache;
import com.lol.scout.exception.ApiFetchFailedException;
import com.lol.scout.service.ApiDataService;
import com.lol.scout.service.ChampionsCacheService;
import com.lol.scout.service.LanguagesCacheService;
import com.lol.scout.service.VersionsCacheService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CacheFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheFacade.class);

    private final ApiDataService apiDataService;
    private final LanguagesCacheService languagesCacheService;
    private final VersionsCacheService versionsCacheService;
    private final ChampionsCacheService championsCacheService;
    private final GsonBuilder gsonBuilder;

    public List<String> getLanguages() {
        List<LanguagesCache> entries = languagesCacheService.getAll();
        LanguagesCache cache;
        if (entries.size()==1 && !(entries.get(0).getLastUpdate().isBefore(LocalDate.now()))) {
            cache = entries.get(0);
        } else {
            LanguagesCache l = new LanguagesCache(LocalDate.now(),gsonBuilder.create().toJson(apiDataService.getLanguages()));
            languagesCacheService.deleteAll();
            cache = languagesCacheService.save(l);
            logCaching();
        }
        return Arrays.asList(gsonBuilder.create().fromJson(cache.getJson(),String[].class));
    }

    public List<String> getVersions() {
        List<VersionsCache> entries = versionsCacheService.getAll();
        VersionsCache cache;
        if (entries.size()==1 && !(entries.get(0).getLastUpdate().isBefore(LocalDate.now()))) {
            cache = entries.get(0);
        } else {
            VersionsCache v = new VersionsCache(LocalDate.now(),gsonBuilder.create().toJson(apiDataService.getVersions()));
            versionsCacheService.deleteAll();
            cache = versionsCacheService.save(v);
            logCaching();
        }
        return Arrays.asList(gsonBuilder.create().fromJson(cache.getJson(),String[].class));
    }

    public ChampionListDto getChampions() throws ApiFetchFailedException {
        List<ChampionsCache> entries = championsCacheService.getAll();
        ChampionsCache cache;
        if (entries.size()==1 && !(entries.get(0).getLastUpdate().isBefore(LocalDate.now()))) {
            cache = entries.get(0);
        } else {
            ChampionsCache c = new ChampionsCache(
                    LocalDate.now(),
                    gsonBuilder.create().toJson(apiDataService.getChampionListDto().orElseThrow(ApiFetchFailedException::new))
            );
            championsCacheService.deleteAll();
            cache = championsCacheService.save(c);
            logCaching();
        }
        return gsonBuilder.create().fromJson(cache.getJson(),ChampionListDto.class);
    }

    private void logCaching() {
        LOGGER.info("Data was cached");
    }

}
