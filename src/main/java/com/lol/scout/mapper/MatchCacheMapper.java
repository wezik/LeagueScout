package com.lol.scout.mapper;

import com.google.gson.GsonBuilder;
import com.lol.scout.domain.cache.MatchCache;
import com.lol.scout.domain.match.MatchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MatchCacheMapper {

    private final GsonBuilder gsonBuilder;

    public MatchDto mapMatchCacheToMatchDto(MatchCache cache) {
        return gsonBuilder.create().fromJson(cache.getJson(),MatchDto.class);
    }

    public MatchCache mapMatchDtoToMatchCache(MatchDto matchDto) {
        return new MatchCache(
                matchDto.getMetaData().getMatchId(),
                gsonBuilder.create().toJson(matchDto),
                matchDto.getInfo().getGameCreation());
    }

}
