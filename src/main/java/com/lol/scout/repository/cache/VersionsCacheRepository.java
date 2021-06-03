package com.lol.scout.repository.cache;

import com.lol.scout.domain.cache.VersionsCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VersionsCacheRepository extends CrudRepository<VersionsCache, Long> {
    List<VersionsCache> findAll();
}
