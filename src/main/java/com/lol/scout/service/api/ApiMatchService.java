package com.lol.scout.service.api;

import com.lol.scout.api.league.client.LeagueMatchApiClient;
import com.lol.scout.domain.match.MatchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiMatchService {

    private final LeagueMatchApiClient leagueMatchApiClient;

    public List<String> getMatchIds(String server, int beginIndex, int endIndex, String puuid) {
        return getMatchIds(server,beginIndex,endIndex,-1,puuid);
    }

    public List<String> getMatchIds(String server, int beginIndex, int endIndex, int queue, String puuid) {
        return leagueMatchApiClient.fetchMatchIds(server,beginIndex,endIndex,queue,puuid);
    }

    public Optional<MatchDto> getMatchData(String server, String id) {
        return leagueMatchApiClient.fetchMatchData(server, id);
    }

}
