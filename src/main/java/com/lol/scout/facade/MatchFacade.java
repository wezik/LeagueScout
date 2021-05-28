package com.lol.scout.facade;

import com.lol.scout.domain.cache.MatchCache;
import com.lol.scout.domain.match.MatchDto;
import com.lol.scout.manager.MatchCacheManager;
import com.lol.scout.mapper.MatchCacheMapper;
import com.lol.scout.service.ApiMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MatchFacade {

    private final MatchCacheManager matchCacheManager;
    private final MatchCacheMapper matchCacheMapper;
    private final ApiMatchService apiMatchService;

    public Optional<MatchDto> getMatchData(String server, String matchIdWithServerId) {
        Optional<MatchCache> optional = matchCacheManager.getMatchDetailsFromCache(matchIdWithServerId);
        if (optional.isPresent()) {
            return Optional.ofNullable(matchCacheMapper.mapMatchCacheToMatchDto(optional.get()));
        }
        optional = matchCacheManager.cacheMatchDetailsFromApi(server, matchIdWithServerId);
        return optional.map(matchCacheMapper::mapMatchCacheToMatchDto);
    }

    public List<String> getMatchIds(String server, int beginIndex, int endIndex, String puuid) {
        return apiMatchService.getMatchIds(server,beginIndex,endIndex,puuid);
    }

}
