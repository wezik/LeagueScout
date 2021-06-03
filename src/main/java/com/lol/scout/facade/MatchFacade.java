package com.lol.scout.facade;

import com.lol.scout.domain.cache.MatchCache;
import com.lol.scout.domain.match.MatchDto;
import com.lol.scout.manager.MatchCacheManager;
import com.lol.scout.mapper.MatchCacheMapper;
import com.lol.scout.service.api.ApiMatchService;
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
        optional = matchCacheManager.cacheMatchDetailsFromApi(server.toUpperCase(), matchIdWithServerId);
        return optional.map(matchCacheMapper::mapMatchCacheToMatchDto);
    }

    public List<String> getMatchIds(String server, int beginIndex, int endIndex, String puuid) {
        return apiMatchService.getMatchIds(server.toUpperCase(),beginIndex,endIndex,puuid);
    }

    public String getQueueName(long queueId) {
        switch(Integer.parseInt(Long.toString(queueId))) {
            case 400: return "Draft Pick";
            case 420: return "Ranked Solo";
            case 430: return "Blind Pick";
            case 440: return "Ranked Flex";
            case 450: return "ARAM";
            case 700: return "CLASH";
            case 830: return "Co-op vs Intro Bots";
            case 840: return "Co-op vs Beginner Bots";
            case 850: return "Co-op vs Intermediate Bots";
            case 900: return "URF";
            default: return "Game mode";
        }
    }

}
