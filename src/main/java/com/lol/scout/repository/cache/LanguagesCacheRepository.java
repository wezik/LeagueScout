package com.lol.scout.repository.cache;

import com.lol.scout.domain.cache.LanguagesCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LanguagesCacheRepository extends CrudRepository<LanguagesCache, LocalDate> {
    List<LanguagesCache> findAll();
}
