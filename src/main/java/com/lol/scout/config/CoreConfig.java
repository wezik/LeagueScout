package com.lol.scout.config;

import com.google.gson.GsonBuilder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties
@Getter
public class CoreConfig {

    @Value("${images.directory}")
    private String imagesDirectory;

    @Value("${update.time.versions}")
    private int updateTimeVersions;

    @Value("${update.time.locales}")
    private int updateTimeLocales;

    @Value("${update.time.queues}")
    private int updateTimeQueues;

    @Value("${update.time.summoner-spells}")
    private int updateTimeSummonerSpells;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public GsonBuilder gsonBuilder() {
        return new GsonBuilder();
    }

}
