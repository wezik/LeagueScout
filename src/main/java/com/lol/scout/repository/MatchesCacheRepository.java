package com.lol.scout.repository;

import com.lol.scout.domain.cache.MatchCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchesCacheRepository extends CrudRepository<MatchCache,String> {
    Optional<MatchCache> findByMatchId(String matchId);
}
