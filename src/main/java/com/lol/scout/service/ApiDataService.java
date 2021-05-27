package com.lol.scout.service;

import com.lol.scout.api.league.client.LeagueDataApiClient;
import com.lol.scout.domain.champion.ChampionListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiDataService {

    private final LeagueDataApiClient leagueDataApiClient;

    public Optional<ChampionListDto> getChampionListDto() {
        return leagueDataApiClient.fetchChampionsList();
    }

    public List<String> getLanguages() {
        return leagueDataApiClient.fetchLanguages();
    }

    public Optional<ChampionListDto> getChampionListDto(String version, String locale) {
        return leagueDataApiClient.fetchChampionsList(version,locale);
    }

    public List<String> getVersions() {
        return leagueDataApiClient.fetchVersions();
    }

}
