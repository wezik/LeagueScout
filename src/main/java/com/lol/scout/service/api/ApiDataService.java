package com.lol.scout.service.api;

import com.lol.scout.api.league.client.LeagueDataApiClient;
import com.lol.scout.domain.queues.QueueDto;
import com.lol.scout.domain.summoners.SummonerSpellsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiDataService {

    private final LeagueDataApiClient leagueDataApiClient;

    public List<String> getLanguages() {
        return leagueDataApiClient.fetchLanguages();
    }

    public List<String> getVersions() {
        return leagueDataApiClient.fetchVersions();
    }

    public Optional<SummonerSpellsDto> getSummonerSpells() {
        return leagueDataApiClient.fetchSummonerSpells();
    }

}
