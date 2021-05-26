package com.lol.scout.service;

import com.lol.scout.api.league.client.LeagueApiClient;
import com.lol.scout.domain.ChampionListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiService {

    private final LeagueApiClient leagueApiClient;

    public Optional<ChampionListDto> getChampionListDto() {
        return leagueApiClient.fetchChampionsList();
    }

}
