package com.lol.scout.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChampionListDto {
    private String type;
    private String format;
    private String version;
    private Map<String, ChampionDto> data;
}
