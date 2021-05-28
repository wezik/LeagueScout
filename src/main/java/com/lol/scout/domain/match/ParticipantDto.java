package com.lol.scout.domain.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParticipantDto {
    @JsonProperty("kills")
    private int kills;
    @JsonProperty("assists")
    private int assists;
    @JsonProperty("deaths")
    private int deaths;
    @JsonProperty("baronKills")
    private int baronKills;
    @JsonProperty("dragonKills")
    private int dragonKills;
    @JsonProperty("bountyLevel")
    private int bountyLevel;
    @JsonProperty("champLevel")
    private int champLevel;
    @JsonProperty("championId")
    private int championId;
    @JsonProperty("championName")
    private String championName;
    @JsonProperty("championTransform")
    private int championTransform;
    @JsonProperty("goldEarned")
    private int goldEarned;
    @JsonProperty("item0")
    private int item0;
    @JsonProperty("item1")
    private int item1;
    @JsonProperty("item2")
    private int item2;
    @JsonProperty("item3")
    private int item3;
    @JsonProperty("item4")
    private int item4;
    @JsonProperty("item5")
    private int item5;
    @JsonProperty("item6")
    private int item6;
    @JsonProperty("lane")
    private String lane;
    @JsonProperty("participantId")
    private int participantId;
    @JsonProperty("puuid")
    private String puuid;
    @JsonProperty("summonerId")
    private String summonerId;
    @JsonProperty("summonerName")
    private String summonerName;
    @JsonProperty("win")
    private boolean win;
}
