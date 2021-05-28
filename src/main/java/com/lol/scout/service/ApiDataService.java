package com.lol.scout.service;

import com.lol.scout.api.league.client.LeagueDataApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
