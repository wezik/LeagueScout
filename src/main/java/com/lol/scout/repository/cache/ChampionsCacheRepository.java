package com.lol.scout.repository.cache;

import com.lol.scout.domain.cache.ChampionsCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChampionsCacheRepository extends CrudRepository<ChampionsCache, String> {
    Optional<ChampionsCache> findByPatch(String patch);
}
