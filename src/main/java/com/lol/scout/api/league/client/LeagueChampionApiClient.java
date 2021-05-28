package com.lol.scout.api.league.client;

import com.lol.scout.api.league.config.LeagueApiConfig;
import com.lol.scout.domain.champion.ChampionListDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LeagueChampionApiClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeagueChampionApiClient.class);

    private final RestTemplate restTemplate;
    private final LeagueApiConfig leagueApiConfig;

    public Optional<ChampionListDto> fetchChampionsList(String version) {
        LOGGER.info("Calling API to fetch champions list");
        try {
            Optional<ChampionListDto> response = Optional.ofNullable(restTemplate.getForObject(
                    buildChampionsListURI(
                            version
                    ),
                    ChampionListDto.class)
            );
            logSuccess();
            return response;
        } catch (RestClientException e) {
            logFail();
            return Optional.empty();
        }
    }

    private URI buildChampionsListURI(String version) {
        List<String> urlComponents = List.of(
                leagueApiConfig.getDDragonEndpoint(),
                "cdn",
                version,
                "data",
                leagueApiConfig.getLocale(),
                "champion.json"
        );
        return UriComponentsBuilder.fromHttpUrl(String.join("/",urlComponents))
                .build().encode().toUri();
    }

    private void logSuccess() {
        LOGGER.info("Call Successful");
    }

    private void logFail() {
        LOGGER.error("Call Failed");
    }

}
