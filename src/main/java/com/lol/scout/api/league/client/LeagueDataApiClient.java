package com.lol.scout.api.league.client;

import com.lol.scout.api.league.config.LeagueApiConfig;
import com.lol.scout.domain.queues.QueueDto;
import com.lol.scout.domain.summoners.SummonerSpellsDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LeagueDataApiClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeagueDataApiClient.class);

    private final RestTemplate restTemplate;
    private final LeagueApiConfig leagueApiConfig;

    public List<String> fetchVersions() {
        LOGGER.info("Calling API to fetch versions");
        try {
            Optional<String[]> response = Optional.ofNullable(restTemplate.getForObject(buildVersionsURI(),String[].class));
            logSuccess();
            return response.map(Arrays::asList).orElse(Collections.emptyList());
        } catch (RestClientException e) {
            logFail();
            return Collections.emptyList();
        }
    }

    public List<String> fetchLanguages() {
        LOGGER.info("Calling API to fetch languages");
        try {
            Optional<String[]> response = Optional.ofNullable(restTemplate.getForObject(buildLanguagesURI(),String[].class));
            logSuccess();
            return response.map(Arrays::asList).orElse(Collections.emptyList());
        } catch (RestClientException e) {
            logFail();
            return Collections.emptyList();
        }
    }

    public Optional<SummonerSpellsDto> fetchSummonerSpells() {
        LOGGER.info("Calling API fo tech summoner spells");
        try {
            Optional<SummonerSpellsDto> response = Optional.ofNullable(restTemplate.getForObject(buildSummonersDtoURI(), SummonerSpellsDto.class));
            logSuccess();
            return response;
        } catch (RestClientException e) {
            logFail();
            return Optional.empty();
        }
    }

    private void logSuccess() {
        LOGGER.info("Call Successful");
    }

    private void logFail() {
        LOGGER.error("Call Failed");
    }

    private URI buildSummonersDtoURI() {
        return UriComponentsBuilder.fromHttpUrl(
                leagueApiConfig.getDDragonEndpoint() +
                        "/cdn/" + leagueApiConfig.getVersion() +
                        "/data/" + leagueApiConfig.getLocale() +
                        "/summoner.json").build().encode().toUri();
    }

    private URI buildVersionsURI() {
        return UriComponentsBuilder.fromHttpUrl(leagueApiConfig.getDDragonEndpoint()+"/api/versions.json")
                .build().encode().toUri();
    }

    private URI buildLanguagesURI() {
        return UriComponentsBuilder.fromHttpUrl(leagueApiConfig.getDDragonEndpoint()+"/cdn/languages.json")
                .build().encode().toUri();
    }

}
