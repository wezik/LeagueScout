package com.lol.scout.api.league.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Getter
public class LeagueApiConfig {
    @Value("${riot.api.key}")
    private String key;

    @Value("${riot.api.eune}")
    private String euneEndpoint;

    @Value("${riot.api.euw}")
    private String euwEndpoint;

    @Value("${riot.api.na}")
    private String naEndpoint;

    @Value("${riot.api.kr}")
    private String krEndpoint;

    @Value("${riot.api.ddragon}")
    private String dDragonEndpoint;

    @Value("${riot.api.static}")
    private String staticEndpoint;

    private String version;

    @Bean
    public void fetchVersion() {
        //TODO Fetch used version from the database
        this.version = "11.11.1";
    }
}
