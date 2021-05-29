package com.lol.scout.mapper;

import com.google.gson.GsonBuilder;
import com.lol.scout.domain.cache.RankCache;
import com.lol.scout.domain.league.LeagueEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class SummonerCacheMapper {

    private final GsonBuilder gsonBuilder;

    public RankCache mapLeagueEntryToRankCache(Set<LeagueEntry> leagueEntry, String server, String summonerId, long lastUpdate) {
        return new RankCache(
                summonerId,
                gsonBuilder.create().toJson(leagueEntry),
                server,lastUpdate
        );
    }

    public Set<LeagueEntry> mapRankCacheToLeagueEntry(RankCache rankCache) {
        return Set.of(gsonBuilder.create().fromJson(rankCache.getJson(),LeagueEntry[].class));
    }

}
