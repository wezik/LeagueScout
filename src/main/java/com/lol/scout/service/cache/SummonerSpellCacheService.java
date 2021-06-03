package com.lol.scout.service.cache;

import com.lol.scout.domain.cache.SummonerSpellCache;
import com.lol.scout.repository.cache.SummonerSpellsCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SummonerSpellCacheService {

    private final SummonerSpellsCacheRepository summonerSpellsCacheRepository;

    public List<SummonerSpellCache> getAll() {
        return summonerSpellsCacheRepository.findAll();
    }

    public void deleteAll() {
        summonerSpellsCacheRepository.deleteAll();
    }

    public SummonerSpellCache save(SummonerSpellCache summonerSpellCache) {
        return summonerSpellsCacheRepository.save(summonerSpellCache);
    }

}
