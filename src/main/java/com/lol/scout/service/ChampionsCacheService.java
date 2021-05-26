package com.lol.scout.service;

import com.lol.scout.domain.cache.ChampionsCache;
import com.lol.scout.repository.ChampionsCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChampionsCacheService {

    private final ChampionsCacheRepository championsCacheRepository;

    public List<ChampionsCache> getAll() {
        return championsCacheRepository.findAll();
    }

    public void deleteAll() {
        championsCacheRepository.deleteAll();
    }

    public ChampionsCache save(ChampionsCache championsCache) {
        return championsCacheRepository.save(championsCache);
    }
}
