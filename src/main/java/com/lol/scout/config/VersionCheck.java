package com.lol.scout.config;

import com.lol.scout.facade.CacheFacade;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VersionCheck {

    private static final Logger LOGGER = LoggerFactory.getLogger(VersionCheck.class);

    private final CacheFacade cacheFacade;

    @Value("${riot.api.version}")
    private String version;

    @Bean
    public void checkVersion() {
        List<String> versions = cacheFacade.getVersions();
        if (versions.size() > 0) {
            String currentVersion = versions.get(0);
            if (!currentVersion.equals(version)) {
                LOGGER.warn("You are not using the current version of the Riot API");
                LOGGER.warn("up to date version: "+currentVersion+" yours: "+version+" change it in application.properties file");
            }
        }
    }
}
