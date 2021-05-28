package com.lol.scout.domain.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchInfoDto {
    @JsonProperty("gameCreation")
    private Long gameCreation;
    @JsonProperty("gameDuration")
    private long gameDuration;
    @JsonProperty("gameId")
    private long gameId;
    @JsonProperty("gameMode")
    private String gameMode;
    @JsonProperty("gameName")
    private String gameName;
    @JsonProperty("gameStartTimestamp")
    private long gameStartTimestamp;
    @JsonProperty("gameType")
    private String gameType;
    @JsonProperty("gameVersion")
    private String gameVersion;
    @JsonProperty("mapId")
    private long mapId;
    @JsonProperty("participants")
    private Set<ParticipantDto> participants;
    @JsonProperty("platformId")
    private String platformId;
    @JsonProperty("queueId")
    private long queueId;
    @JsonProperty("teams")
    private Set<TeamDto> teams;
}
