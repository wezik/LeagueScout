package com.lol.scout.service;

import com.lol.scout.api.league.client.LeagueSummonerApiClient;
import com.lol.scout.domain.currentgame.CurrentGameInfo;
import com.lol.scout.domain.league.LeagueEntry;
import com.lol.scout.domain.summoner.Summoner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ApiSummonerService {

    private final LeagueSummonerApiClient leagueSummonerApiClient;

    public Optional<Summoner> getSummonerByName(String server, String summonerName ) {
        return leagueSummonerApiClient.fetchSummonerByName(server,summonerName);
    }

    public Optional<Summoner> getSummonerByPuuid(String server, String puuid) {
        return leagueSummonerApiClient.fetchSummonerByPuuid(server,puuid);
    }

    public Optional<CurrentGameInfo> getCurrentGameById(String server, String summonerId) {
        return leagueSummonerApiClient.fetchLiveGame(server,summonerId);
    }

    public Set<LeagueEntry> getRankEntries(String server, String summonerId) {
        return leagueSummonerApiClient.fetchRankEntries(server,summonerId);
    }

}
