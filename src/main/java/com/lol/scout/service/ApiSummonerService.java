package com.lol.scout.service;

import com.lol.scout.api.league.client.LeagueSummonerApiClient;
import com.lol.scout.domain.currentgame.CurrentGameInfo;
import com.lol.scout.domain.summoner.Summoner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiSummonerService {

    private final LeagueSummonerApiClient leagueSummonerApiClient;

    public Optional<Summoner> getSummonerByName(String server, String summonerName ) {
        return leagueSummonerApiClient.fetchSummonerByName(server,summonerName);
    }

    public Optional<CurrentGameInfo> getCurrentGameById(String server, String summonerId) {
        return leagueSummonerApiClient.fetchLiveGame(server,summonerId);
    }

}
