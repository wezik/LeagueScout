package com.lol.scout.controller;

import com.lol.scout.domain.ChampionListDto;
import com.lol.scout.exception.ApiFetchFailedException;
import com.lol.scout.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MainController {

    private final ApiService apiService;

    @GetMapping("champions")
    public List<String> getChampions() throws ApiFetchFailedException {
        ChampionListDto championListDto = apiService.getChampionListDto().orElseThrow(ApiFetchFailedException::new);
        List<String> champions = new ArrayList<>(championListDto.getData().keySet());
        Collections.sort(champions);
        return champions;
    }

}
