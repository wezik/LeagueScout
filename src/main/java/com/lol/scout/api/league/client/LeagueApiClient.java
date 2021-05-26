package com.lol.scout.api.league.client;

import com.lol.scout.api.league.config.LeagueApiConfig;
import com.lol.scout.domain.ChampionListDto;
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
public class LeagueApiClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeagueApiClient.class);

    private final RestTemplate restTemplate;
    private final LeagueApiConfig leagueApiConfig;


    public Optional<ChampionListDto> fetchChampionsList() {
        try {
            LOGGER.info("Calling API to fetch champions list");
            Optional<ChampionListDto> response = Optional.ofNullable(restTemplate.getForObject(buildChampionsListURI(), ChampionListDto.class));
            LOGGER.info("Call Successful");
            return response;
        } catch (RestClientException e) {
            LOGGER.error("Call Failed");
            return Optional.empty();
        }
    }

    private URI buildChampionsListURI() {
        List<String> urlComponents = List.of(
                leagueApiConfig.getDDragonEndpoint(),
                "cdn",
                leagueApiConfig.getVersion(),
                "data",
                "en_US",
                "champion.json"
        );
        return UriComponentsBuilder.fromHttpUrl(String.join("/",urlComponents)).build().encode().toUri();
    }

}
