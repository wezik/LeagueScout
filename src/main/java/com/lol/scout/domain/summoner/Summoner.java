package com.lol.scout.domain.summoner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "summoners")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Summoner {
    @Id
    @JsonProperty("puuid")
    private String puuid;
    @JsonProperty("accountId")
    private String accountId;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("profileIconId")
    private long profileIconId;
    @JsonProperty("revisionDate")
    private long revisionDate;
    @JsonProperty("summonerLevel")
    private long summonerLevel;
    private long lastUpdate;
    private String region;
}
