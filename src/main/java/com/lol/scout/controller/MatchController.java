package com.lol.scout.controller;

import com.lol.scout.domain.match.MatchDto;
import com.lol.scout.exception.MatchNotFoundException;
import com.lol.scout.facade.MatchFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/match")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MatchController {

    private final MatchFacade matchFacade;

    @GetMapping("{server}/{matchId}/details")
    public MatchDto getMatchDetails(@PathVariable String server, @PathVariable String matchId) throws MatchNotFoundException {
        return matchFacade.getMatchData(server,matchId).orElseThrow(MatchNotFoundException::new);
    }

    @GetMapping("{server}/{puuid}/ids")
    public List<String> getMatchIds(
            @PathVariable String server,
            @PathVariable String puuid,
            @RequestParam(required = false) String beginIndex,
            @RequestParam(required = false) String endIndex) {
        int bI = beginIndex!=null ? Integer.parseInt(beginIndex) : 0;
        int eI = endIndex!=null ? Integer.parseInt(endIndex) : 20;
        return matchFacade.getMatchIds(server,bI,eI,puuid);
    }

}
