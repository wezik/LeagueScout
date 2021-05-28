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
@Entity(name = "matches_cache")
public class MatchCache {
    @Id
    private String matchId;
    @Lob
    private String json;
    private long gameCreation;
}
