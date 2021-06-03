package com.lol.scout.controller;

import com.lol.scout.domain.endpoint.MatchHistoryEntryDto;
import com.lol.scout.domain.match.MatchDto;
import com.lol.scout.domain.match.ParticipantDto;
import com.lol.scout.facade.MatchFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ApiController {

    private final MatchFacade matchFacade;

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
