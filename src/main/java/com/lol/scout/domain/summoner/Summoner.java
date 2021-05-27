package com.lol.scout.domain.summoner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Summoner {
    private String id;
    private String accountId;
    private String puuid;
    private String name;
    private long profileIconId;
    private long revisionDate;
    private long summonerLevel;
}
