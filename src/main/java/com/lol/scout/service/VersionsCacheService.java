package com.lol.scout.service;

import com.lol.scout.domain.cache.VersionsCache;
import com.lol.scout.repository.VersionsCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VersionsCacheService {

    private final VersionsCacheRepository versionsCacheRepository;

    public List<VersionsCache> getAll() {
        return versionsCacheRepository.findAll();
    }

    public void deleteAll() {
        versionsCacheRepository.deleteAll();
    }

    public VersionsCache save(VersionsCache versionsCache) {
        return versionsCacheRepository.save(versionsCache);
    }
}
