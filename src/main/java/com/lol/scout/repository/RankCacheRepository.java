package com.lol.scout.repository;

import com.lol.scout.domain.cache.RankCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RankCacheRepository extends CrudRepository<RankCache,String> {
    Optional<RankCache> findBySummonerId(String summonerId);
}
