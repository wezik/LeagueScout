package com.lol.scout.api.league.config;

import com.lol.scout.facade.DataCacheFacade;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VersionCheck {

    private static final Logger LOGGER = LoggerFactory.getLogger(VersionCheck.class);

    private final DataCacheFacade dataCacheFacade;

    @Value("${riot.api.version}")
    private String version;

    @Value("${riot.api.autoupdate}")
    private String autoUpdate;

    private final String springProperty = "riot.api.version=";
    private final String propertiesFilePath = "src/main/resources/application.properties";

    @Bean
    public void checkVersion() {
        List<String> versions = dataCacheFacade.getVersions();
        if (versions.size() > 0) {
            String currentVersion = versions.get(0);
            if (!currentVersion.equals(version)) {
                LOGGER.warn("You are not using the current version of the Riot API");
                if (Integer.parseInt(autoUpdate)==1) {
                    LOGGER.warn("up to date version: " + currentVersion + " yours: " + version + " program is going to update it");
                    updateVersion(currentVersion);
                }
            }
        }
    }

    private void updateVersion(String version) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(propertiesFilePath));
            List<String> lines = br.lines().collect(Collectors.toList());
            br.close();
            for (int i=0; i<lines.size(); i++) {
                String s = lines.get(i);
                if (s.contains(springProperty)) {
                    String resultString = springProperty+version;
                    lines.set(i,resultString);
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(propertiesFilePath, false));
            for (String line : lines) {
                bw.write(line+"\n");
            }
            bw.flush();
            bw.close();
            LOGGER.info("API updated successfully");
        } catch (IOException e) {
            LOGGER.error("Failed to update Riot API");
        }
    }
}
