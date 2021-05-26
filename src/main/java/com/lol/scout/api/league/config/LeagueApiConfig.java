package com.lol.scout.api.league.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${riot.api.locale}")
    private String locale;

    @Value("${riot.api.version}")
    private String version;

}
