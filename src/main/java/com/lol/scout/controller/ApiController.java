package com.lol.scout.controller;

import com.lol.scout.api.league.config.LeagueApiConfig;
import com.lol.scout.domain.champion.ChampionDto;
import com.lol.scout.domain.champion.ChampionListDto;
import com.lol.scout.domain.currentgame.CurrentGameInfo;
import com.lol.scout.domain.endpoint.LiveGameDto;
import com.lol.scout.domain.endpoint.LiveGameParticipantDto;
import com.lol.scout.domain.endpoint.MatchHistoryEntryDto;
import com.lol.scout.domain.match.MatchDto;
import com.lol.scout.domain.match.ParticipantDto;
import com.lol.scout.domain.summoner.Summoner;
import com.lol.scout.exception.ApiFetchFailedException;
import com.lol.scout.exception.SummonerNotFoundException;
import com.lol.scout.exception.SummonerNotInGameException;
import com.lol.scout.facade.ChampionFacade;
import com.lol.scout.facade.MatchFacade;
import com.lol.scout.facade.SummonerFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ApiController {

    private final MatchFacade matchFacade;
    private final SummonerFacade summonerFacade;
    private final ChampionFacade championFacade;
    private final LeagueApiConfig leagueApiConfig;

    @GetMapping("live/{server}/{name}")
    public LiveGameDto getLiveGame(@PathVariable String server, @PathVariable String name)
            throws SummonerNotFoundException, SummonerNotInGameException, ApiFetchFailedException {

        Summoner summoner = summonerFacade.getSummonerByName(server, name)
                .orElseThrow(SummonerNotFoundException::new);
        CurrentGameInfo currentGameInfo = summonerFacade.getLiveGame(server,summoner.getId())
                .orElseThrow(SummonerNotInGameException::new);

        List<LiveGameParticipantDto> team1 = new ArrayList<>();
        List<LiveGameParticipantDto> team2 = new ArrayList<>();

        ChampionListDto champions = championFacade.getChampions(leagueApiConfig.getVersion())
                .orElseThrow(ApiFetchFailedException::new);

        Map<Long, String> championsMap = new HashMap<>();

        champions.getData().forEach((key, value) -> championsMap.put(Long.parseLong(value.getKey()), key));

        currentGameInfo.getParticipants().forEach(
                e -> {
                    if (e.getTeamId()==100) {
                        team1.add(new LiveGameParticipantDto(
                                e.getSummonerName(),
                                championsMap.get(e.getChampionId()),
                                0,0,0,0,0,
                                summonerFacade.getRankEntries(server,e.getSummonerId())
                        ));
                    } else {
                        team2.add(new LiveGameParticipantDto(
                                e.getSummonerName(),
                                championsMap.get(e.getChampionId()),
                                0,0,0,0,0,
                                summonerFacade.getRankEntries(server,e.getSummonerId())
                        ));
                    }
                }
        );

        return new LiveGameDto(
                matchFacade.getQueueName(currentGameInfo.getGameQueueConfigId()),
                currentGameInfo.getGameLength(),
                team1,
                team2
        );
    }

    @GetMapping("history/{server}/{puuid}/{startIndex}/{endIndex}")
    public List<MatchHistoryEntryDto> getMatchHistory(
            @PathVariable String server,
            @PathVariable String puuid,
            @PathVariable int startIndex,
            @PathVariable int endIndex
    ) {
        List<String> matchIds = matchFacade.getMatchIds(server,startIndex,endIndex,puuid);
        List<MatchHistoryEntryDto> resultList = new ArrayList<>();

        for (String matchId : matchIds) {
            MatchDto matchDto = matchFacade.getMatchData(server,matchId).orElse(new MatchDto());
            if (matchDto.getInfo()!=null) {
                MatchHistoryEntryDto entry = new MatchHistoryEntryDto(
                        matchDto.getInfo().getGameCreation(),
                        matchDto.getInfo().getGameDuration(),
                        matchFacade.getQueueName(matchDto.getInfo().getQueueId()),
                        matchDto.getInfo().getGameVersion(),
                        null,
                        null,
                        null
                );
                List<ParticipantDto> team1 = new ArrayList<>();
                List<ParticipantDto> team2 = new ArrayList<>();
                for(ParticipantDto participant : matchDto.getInfo().getParticipants()) {
                    if (participant.getPuuid().equalsIgnoreCase(puuid)) {
                        entry.setSummoner(participant);
                    }
                    if (participant.getTeamId()==100) team1.add(participant);
                    else team2.add(participant);
                }
                entry.setTeam1(team1);
                entry.setTeam2(team2);
                resultList.add(entry);
            }
        }
        return resultList;
    }

}
