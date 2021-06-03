package com.lol.scout.controller;

import com.lol.scout.domain.currentgame.CurrentGameInfo;
import com.lol.scout.domain.league.LeagueEntry;
import com.lol.scout.domain.match.MatchDto;
import com.lol.scout.domain.summoner.Summoner;
import com.lol.scout.exception.SummonerNotFoundException;
import com.lol.scout.exception.SummonerNotInDatabaseException;
import com.lol.scout.exception.SummonerNotInGameException;
import com.lol.scout.facade.SummonerFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("v1/summoner")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SummonerController {

    private final SummonerFacade summonerFacade;

    @GetMapping("{server}/{summoner_id}/live")
    public CurrentGameInfo getCurrentGameInfo(@PathVariable String server, @PathVariable String summoner_id) throws SummonerNotInGameException {
        return summonerFacade.getLiveGame(server,summoner_id).orElseThrow(SummonerNotInGameException::new);
    }

    @GetMapping("{server}/{summoner}")
    public Summoner getSummoner(@PathVariable String server, @PathVariable String summoner) throws SummonerNotFoundException {
        return summonerFacade.getSummonerByName(server,summoner).orElseThrow(SummonerNotFoundException::new);
    }

    @GetMapping("{puuid}")
    public Summoner getSummonerByPuuid(@PathVariable String puuid) throws SummonerNotInDatabaseException {
        return summonerFacade.getSummonerByPuuid(puuid).orElseThrow(SummonerNotInDatabaseException::new);
    }

    @GetMapping("{server}/{summoner_id}/rank")
    public Set<LeagueEntry> getRanks(@PathVariable String server, @PathVariable String summoner_id) {
        return summonerFacade.getRankEntries(server, summoner_id);
    }

}
