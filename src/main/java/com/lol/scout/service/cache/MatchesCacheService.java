package com.lol.scout.service.cache;

import com.lol.scout.domain.cache.MatchCache;
import com.lol.scout.repository.cache.MatchesCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchesCacheService {

    private final MatchesCacheRepository matchesCacheRepository;

    public Optional<MatchCache> getById(String id) {
        return matchesCacheRepository.findByMatchId(id);
    }

    public MatchCache save(MatchCache matchCache) {
        return matchesCacheRepository.save(matchCache);
    }
}
