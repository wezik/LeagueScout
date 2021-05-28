package com.lol.scout.domain.summoner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private long championId;
    private boolean win;
    private int kills;
    private int deaths;
    private int assists;
}
