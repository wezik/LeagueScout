package com.lol.scout.service.cache;

import com.lol.scout.domain.cache.ChampionsCache;
import com.lol.scout.repository.cache.ChampionsCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChampionsCacheService {

    private final ChampionsCacheRepository championsCacheRepository;

    public Optional<ChampionsCache> get(String patch) {
        return championsCacheRepository.findByPatch(patch);
    }

    public ChampionsCache save(ChampionsCache championsCache) {
        return championsCacheRepository.save(championsCache);
    }
}
