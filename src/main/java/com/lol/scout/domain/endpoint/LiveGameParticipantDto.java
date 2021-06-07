package com.lol.scout.domain.endpoint;

import com.lol.scout.domain.league.LeagueEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LiveGameParticipantDto {
    private String summonerName;
    private String championName;
    private int championWins;
    private int championLosses;
    private int championKills;
    private int championDeaths;
    private int championAssists;
    private Set<LeagueEntry> ranks;
}
