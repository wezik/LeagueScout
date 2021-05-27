package com.lol.scout.controller;

import com.lol.scout.domain.currentgame.CurrentGameInfo;
import com.lol.scout.domain.league.LeagueEntry;
import com.lol.scout.domain.summoner.Summoner;
import com.lol.scout.exception.SummonerNotFoundException;
import com.lol.scout.exception.SummonerNotInGame;
import com.lol.scout.service.ApiSummonerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("v1/summoner")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SummonerController {

    private final ApiSummonerService apiSummonerService;

    @GetMapping("{server}/{summoner_id}/live")
    public CurrentGameInfo getCurrentGameInfo(@PathVariable String server, @PathVariable String summoner_id) throws SummonerNotInGame {
        return apiSummonerService.getCurrentGameById(server,summoner_id).orElseThrow(SummonerNotInGame::new);
    }

    @GetMapping("{server}/{summoner}")
    public Summoner getSummoner(@PathVariable String server, @PathVariable String summoner) throws SummonerNotFoundException {
        return apiSummonerService.getSummonerByName(server,summoner).orElseThrow(SummonerNotFoundException::new);
    }

    @GetMapping("{server}/{summoner_id}/rank")
    public Set<LeagueEntry> getRanks(@PathVariable String server, @PathVariable String summoner_id) {
        return apiSummonerService.getRankEntries(server,summoner_id);
    }

    private String getSummonerIdForName(String server, String name) throws SummonerNotFoundException {
        Summoner summoner = apiSummonerService.getSummonerByName(server,name).orElseThrow(SummonerNotFoundException::new);
        return summoner.getId();
    }


}
