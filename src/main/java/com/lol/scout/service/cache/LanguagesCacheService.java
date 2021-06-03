package com.lol.scout.service.cache;

import com.lol.scout.domain.cache.LanguagesCache;
import com.lol.scout.repository.cache.LanguagesCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguagesCacheService {

    private final LanguagesCacheRepository languagesCacheRepository;

    public List<LanguagesCache> getAll() {
        return languagesCacheRepository.findAll();
    }

    public void deleteAll() {
        languagesCacheRepository.deleteAll();
    }

    public LanguagesCache save(LanguagesCache languagesCache) {
        return languagesCacheRepository.save(languagesCache);
    }
}
