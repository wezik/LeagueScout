package com.lol.scout.service.api;

import com.lol.scout.api.league.client.LeagueChampionApiClient;
import com.lol.scout.domain.champion.ChampionListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiChampionService {

    private final LeagueChampionApiClient leagueChampionApiClient;

    public Optional<ChampionListDto> getChampionListDto(String version) {
        return leagueChampionApiClient.fetchChampionsList(version);
    }

}
