package com.lol.scout.service;

import com.lol.scout.domain.summoner.ChampionStats;
import com.lol.scout.repository.ChampionStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChampionStatsService {

    private final ChampionStatsRepository championStatsRepository;

    public ChampionStats save(ChampionStats stats) {
        return championStatsRepository.save(stats);
    }
}
