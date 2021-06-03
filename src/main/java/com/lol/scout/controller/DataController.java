package com.lol.scout.controller;

import com.lol.scout.facade.DataFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("v1/data")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DataController {

    private final DataFacade dataFacade;

    @GetMapping("versions")
    public List<String> getVersions() {
        return dataFacade.getVersions();
    }

    @GetMapping("languages")
    public List<String> getLanguages() {
        return dataFacade.getLanguages();
    }

}
