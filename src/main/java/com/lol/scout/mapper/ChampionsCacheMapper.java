package com.lol.scout.mapper;

import com.google.gson.GsonBuilder;
import com.lol.scout.domain.cache.ChampionsCache;
import com.lol.scout.domain.champion.ChampionListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChampionsCacheMapper {

    private final GsonBuilder gsonBuilder;

    public ChampionListDto mapChampionsCacheToChampionListDto(ChampionsCache championsCache) {
        return gsonBuilder.create().fromJson(championsCache.getJson(), ChampionListDto.class);
    }

    public ChampionsCache mapChampionListDtoToChampionsCache(ChampionListDto championListDto, String patch) {
        return new ChampionsCache(patch,gsonBuilder.create().toJson(championListDto));
    }

}
