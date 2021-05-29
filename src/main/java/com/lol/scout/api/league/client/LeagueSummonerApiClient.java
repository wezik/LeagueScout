package com.lol.scout.api.league.client;

import com.lol.scout.api.league.config.LeagueApiConfig;
import com.lol.scout.domain.currentgame.CurrentGameInfo;
import com.lol.scout.domain.league.LeagueEntry;
import com.lol.scout.domain.summoner.Summoner;
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
public class LeagueSummonerApiClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeagueSummonerApiClient.class);

    private final RestTemplate restTemplate;
    private final LeagueApiConfig leagueApiConfig;

    public Set<LeagueEntry> fetchRankEntries(String server, String summonerId) {
        LOGGER.info("Calling API to fetch rank");
        try {
            Optional<LeagueEntry[]> response = Optional.ofNullable(
                    restTemplate.getForObject(
                            buildRankEntriesByIdURI(server, summonerId),
                            LeagueEntry[].class));
            if (response.isPresent()) {
                logSuccess();
                return new HashSet<>(Arrays.asList(response.get()));
            } else {
                logFail();
            }
        }   catch(RestClientException e){
                logFail();
            }
        return Collections.emptySet();
    }

    public Optional<CurrentGameInfo> fetchLiveGame(String server, String summonerId) {
        LOGGER.info("Calling API to fetch live game");
        try {
            Optional<CurrentGameInfo> response = Optional.ofNullable(
                    restTemplate.getForObject(
                            buildLiveGameBySummonerIdURI(server, summonerId),
                            CurrentGameInfo.class)
            );
            logSuccess();
            return response;
        } catch (RestClientException e) {
            logFail();
            return Optional.empty();
        }
    }

    public Optional<Summoner> fetchSummonerByName(String server, String name) {
        LOGGER.info("Calling API to fetch summoner "+name);
        try {
            Optional<Summoner> response = Optional.ofNullable(
                    restTemplate.getForObject(
                            buildSummonerByNameURI(server, name),
                            Summoner.class)
            );
            logSuccess();
            return response;
        } catch (RestClientException e) {
            logFail();
            return Optional.empty();
        }
    }

    public Optional<Summoner> fetchSummonerByPuuid(String server, String puuid) {
        LOGGER.info("Calling API to fetch summoner "+puuid);
        try {
            Optional<Summoner> response = Optional.ofNullable(
                    restTemplate.getForObject(
                            buildSummonerByPuuidUri(server, puuid),
                            Summoner.class)
            );
            logSuccess();
            return response;
        } catch (RestClientException e) {
            logFail();
            return Optional.empty();
        }
    }

    private final String apiKeyParam = "?api_key=";
    private final String summonerApi = "summoner";
    private final String spectatorApi = "spectator";
    private final String leagueApi = "league";
    private final String apiName = "lol";
    private final String apiVersion = "v4";
    private final String byName = "by-name";
    private final String byId = "by-summoner";
    private final String byPuuid = "by-puuid";

    private URI buildRankEntriesByIdURI(String server, String summonerId) {
        List<String> params = List.of(
                determineServerEndpoint(server),
                apiName,
                leagueApi,
                apiVersion,
                "entries",
                byId,
                summonerId+apiKeyParam+leagueApiConfig.getKey()
        );
        return UriComponentsBuilder.fromHttpUrl(String.join("/",params))
                .build().encode().toUri();
    }

    private URI buildSummonerByNameURI(String server, String name) {
        List<String> params = List.of(
                determineServerEndpoint(server),
                apiName,
                summonerApi,
                apiVersion,
                "summoners",
                byName,
                name+apiKeyParam+leagueApiConfig.getKey()
        );
        return UriComponentsBuilder.fromHttpUrl(String.join("/", params))
                .build().encode().toUri();
    }

    private URI buildSummonerByPuuidUri(String server, String puuid) {
        List<String> params = List.of(
                determineServerEndpoint(server),
                apiName,
                summonerApi,
                apiVersion,
                "summoners",
                byPuuid,
                puuid+apiKeyParam+leagueApiConfig.getKey()
        );
        return UriComponentsBuilder.fromHttpUrl(String.join("/", params))
                .build().encode().toUri();
    }

    private URI buildLiveGameBySummonerIdURI(String server, String summonerId) {
        List<String> params = List.of(
                determineServerEndpoint(server),
                apiName,
                spectatorApi,
                apiVersion,
                "active-games",
                byId,
                summonerId+apiKeyParam+leagueApiConfig.getKey()
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
        if (server.equalsIgnoreCase("eune")) return leagueApiConfig.getEuneEndpoint();
        else if (server.equalsIgnoreCase("na")) return leagueApiConfig.getNaEndpoint();
        else if (server.equalsIgnoreCase("kr")) return leagueApiConfig.getKrEndpoint();
        else return leagueApiConfig.getEuwEndpoint();
    }

}
