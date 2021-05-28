package com.lol.scout.controller;

import com.lol.scout.api.league.config.LeagueApiConfig;
import com.lol.scout.domain.champion.ChampionDetailsDto;
import com.lol.scout.domain.champion.ChampionListDto;
import com.lol.scout.exception.ApiFetchFailedException;
import com.lol.scout.facade.ChampionFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/champion")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChampionsController {

    private final ChampionFacade championFacade;
    private final LeagueApiConfig leagueApiConfig;

    @GetMapping("list")
    public ChampionListDto getChampions(@RequestParam(name = "patch", required = false) String patch) throws ApiFetchFailedException {
        if (patch==null) patch = leagueApiConfig.getVersion();
        return championFacade.getChampions(patch).orElseThrow(ApiFetchFailedException::new);
    }

    @GetMapping("{champion}")
    public ChampionDetailsDto getChampionDetails(
            @PathVariable String champion,
            @RequestParam(name = "patch", required = false) String patch)
            throws ApiFetchFailedException {
        if (patch.isEmpty()) patch = leagueApiConfig.getVersion();
        return championFacade.getChampionDetails(champion,patch).orElseThrow(ApiFetchFailedException::new);
    }

}
