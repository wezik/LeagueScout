package com.lol.scout.domain.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "rank_cache")
public class RankCache {
    @Id
    private String summonerId;
    @Lob
    private String json;
    private String region;
    private long lastUpdate;
}
