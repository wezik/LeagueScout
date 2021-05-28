package com.lol.scout.api.league.client;

import com.lol.scout.api.league.config.LeagueApiConfig;
import com.lol.scout.domain.match.MatchDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Component
@RequiredArgsConstructor
public class LeagueMatchApiClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeagueMatchApiClient.class);

    private final RestTemplate restTemplate;
    private final LeagueApiConfig leagueApiConfig;

    public List<String> fetchMatchIds(String server, int beginIndex, int endIndex, int queue, String puuid) {
        LOGGER.info("Calling API to fetch match ids");
        try {
            Optional<String[]> response = Optional.ofNullable(
                    restTemplate.getForObject(
                            buildMatchIdsURI(server,beginIndex,endIndex,queue,puuid)
                            ,String[].class)
            );
            logSuccess();
            if (response.isPresent()) {
                return Arrays.asList(response.get());
            }
        } catch (RestClientException e){
            logFail();
        }
        return Collections.emptyList();
    }

    public Optional<MatchDto> fetchMatchData(String server, String id) {
        LOGGER.info("Calling API to fetch match data");
        try {
            Optional<MatchDto> response = Optional.ofNullable(restTemplate.getForObject(buildMatchDataURI(server,id),MatchDto.class));
            logSuccess();
            return response;
        } catch (RestClientException e) {
            logFail();
        }
        return Optional.empty();
    }

    private URI buildMatchIdsURI(String server, int beginIndex, int endIndex, int queue, String puuid) {
        List<String> params = List.of(
                determineServerEndpoint(server),
                "/lol/match/v5/matches/by-puuid/",
                puuid,
                "/ids",
                "?start="+beginIndex,
                "&count="+endIndex,
                (queue>0) ? "&queue="+queue : "",
                "&api_key="+leagueApiConfig.getKey()
        );
        return UriComponentsBuilder.fromHttpUrl(String.join("",params))
                .build().encode().toUri();
    }

    private URI buildMatchDataURI(String server, String id) {
        List<String> params = List.of(
                determineServerEndpoint(server),
                "lol/match/v5/matches",
                id+"?api_key="+leagueApiConfig.getKey()
        );
        return UriComponentsBuilder.fromHttpUrl(String.join("/",params))
                .build().encode().toUri();
    }

    private void logSuccess() {
        LOGGER.info("Call Successful");
    }

    private void logFail() {
        LOGGER.error("Call Failed");
    }

    private String determineServerEndpoint(String server) {
        if (server.equalsIgnoreCase("eune")) return "https://europe.api.riotgames.com";
        else if (server.equalsIgnoreCase("na")) return "https://americas.api.riotgames.com";
        else if (server.equalsIgnoreCase("kr")) return "https://asia.api.riotgames.com";
        else return "https://europe.api.riotgames.com";
    }

}
