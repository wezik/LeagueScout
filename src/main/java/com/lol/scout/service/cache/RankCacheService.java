package com.lol.scout.service.cache;

import com.lol.scout.domain.cache.RankCache;
import com.lol.scout.repository.cache.RankCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RankCacheService {

    private final RankCacheRepository rankCacheRepository;

    public Optional<RankCache> getBySummonerId(String summonerId) {
        return rankCacheRepository.findBySummonerId(summonerId);
    }

    public RankCache save(RankCache rankCache) {
        return rankCacheRepository.save(rankCache);
    }

}
