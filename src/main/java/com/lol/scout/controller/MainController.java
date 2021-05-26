package com.lol.scout.controller;

import com.lol.scout.domain.ChampionListDto;
import com.lol.scout.exception.ApiFetchFailedException;
import com.lol.scout.facade.CacheFacade;
import com.lol.scout.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MainController {

    private final ApiService apiService;
    private final CacheFacade cacheFacade;

    @GetMapping("champions")
    public ChampionListDto getChampions() throws ApiFetchFailedException {
        return cacheFacade.getChampions();
    }

    @GetMapping("champions/{version}/{locale}")
    public ChampionListDto getChampions(@PathVariable String version, @PathVariable String locale) throws ApiFetchFailedException {
        return apiService.getChampionListDto(version,locale).orElseThrow(ApiFetchFailedException::new);
    }

    @GetMapping("versions")
    public List<String> getVersions() {
        return cacheFacade.getVersions();
    }

    @GetMapping("languages")
    public List<String> getLanguages() {
        return cacheFacade.getLanguages();
    }

}
