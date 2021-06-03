package com.lol.scout.repository.cache;

import com.lol.scout.domain.cache.SummonerSpellCache;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SummonerSpellsCacheRepository extends CrudRepository<SummonerSpellCache,Long> {
    List<SummonerSpellCache> findAll();
}
