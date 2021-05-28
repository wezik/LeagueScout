package com.lol.scout.domain.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamDto {
    @JsonProperty("bans")
    private Set<BanDto> bans;
    @JsonProperty("objectives")
    private Map<String,ObjectiveDto> objectives;
    @JsonProperty("teamId")
    private int teamId;
    @JsonProperty("win")
    private boolean win;
}
