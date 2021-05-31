package com.lol.scout.service;

import com.lol.scout.domain.summoner.ChampionStats;
import com.lol.scout.repository.ChampionStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChampionStatsService {

    private final ChampionStatsRepository championStatsRepository;

    public List<ChampionStats> getAll() {
        return championStatsRepository.findAll();
    }

    public ChampionStats save(ChampionStats stats) {
        return championStatsRepository.save(stats);
    }

}
