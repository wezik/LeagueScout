package com.lol.scout.mapper;

import com.google.gson.GsonBuilder;
import com.lol.scout.domain.cache.LanguagesCache;
import com.lol.scout.domain.cache.SummonerSpellCache;
import com.lol.scout.domain.cache.VersionsCache;
import com.lol.scout.domain.summoners.SummonerSpellsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataCacheMapper {

    private final GsonBuilder gsonBuilder;

    public List<String> mapLanguagesCacheToList(LanguagesCache languagesCache) {
        return new ArrayList<>(Arrays.asList(
                gsonBuilder.create().fromJson(languagesCache.getJson(),String[].class)
        ));
    }

    public LanguagesCache mapListToLanguagesCache(List<String> languages, LocalDate dateOfCache) {
        return new LanguagesCache(dateOfCache,gsonBuilder.create().toJson(languages));
    }

    public List<String> mapVersionsCacheToList(VersionsCache versionsCache) {
        return new ArrayList<>(Arrays.asList(
                gsonBuilder.create().fromJson(versionsCache.getJson(),String[].class)
        ));
    }

    public VersionsCache mapListToVersionsCache(List<String> versions, Long dateOfCache) {
        return new VersionsCache(dateOfCache,gsonBuilder.create().toJson(versions));
    }

    public SummonerSpellCache mapSummonerSpellsDtoToSummonerSpellCache(SummonerSpellsDto summonerSpellsDto, Long dateOfCache) {
        return new SummonerSpellCache(dateOfCache,gsonBuilder.create().toJson(summonerSpellsDto));
    }

    public SummonerSpellsDto maSummonerSpellCacheToSummonerSpellsDto(SummonerSpellCache cache) {
        return gsonBuilder.create().fromJson(cache.getJson(),SummonerSpellsDto.class);
    }

}
