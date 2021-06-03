package com.lol.scout.domain.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "summoner_spell_cache")
public class SummonerSpellCache {
    @Id
    private long lastUpdate;
    @Lob
    private String json;
}
