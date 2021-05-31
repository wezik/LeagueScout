package com.lol.scout.domain.summoner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "champion_stats")
public class ChampionStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String matchId;
    private String summonerPuuid;
    private long queueId;
    private String championKey;
    private boolean win;
    private int kills;
    private int deaths;
    private int assists;
    private String patch;
    private long mapId;
    private String role;
}
