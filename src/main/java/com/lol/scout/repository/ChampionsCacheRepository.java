package com.lol.scout.repository;

import com.lol.scout.domain.cache.ChampionsCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChampionsCacheRepository extends CrudRepository<ChampionsCache, LocalDate> {
    List<ChampionsCache> findAll();
}
