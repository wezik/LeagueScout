package com.lol.scout.manager;

import com.lol.scout.api.league.config.LeagueApiConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AssetUrlManager {

    //TODO cache for assets
    private final LeagueApiConfig leagueApiConfig;

    public String createIconArtUrlForItem(String id) {
        List<String> params = List.of(
                leagueApiConfig.getDDragonEndpoint(),
                "cdn",
                leagueApiConfig.getVersion(),
                "img/item",
                id+".png"
        );
        return String.join("/",params);
    }

    public String createIconArtUrlForSummonerSpell(String id) {
        List<String> params = List.of(
                leagueApiConfig.getDDragonEndpoint(),
                "cdn",
                leagueApiConfig.getVersion(),
                "img/spell",
                id+".png"
        );
        return String.join("/",params);
    }

    public String createIconArtUrlForSummoner(String id) {
        List<String> params = List.of(
                leagueApiConfig.getDDragonEndpoint(),
                "cdn",
                leagueApiConfig.getVersion(),
                "img/profileicon",
                id+".png"
        );
        return String.join("/",params);
    }

    public String createIconArtUrlForChampion(String champion) {
        List<String> params = List.of(
                leagueApiConfig.getDDragonEndpoint(),
                "cdn",
                leagueApiConfig.getVersion(),
                "img/champion",
                champion+".png"
        );
        return String.join("/",params);
    }

    public String createLoadingArtUrlForChampion(String champion) {
        return createLoadingArtUrlForChampion(champion,0);
    }

    public String createLoadingArtUrlForChampion(String champion, int skin) {
        List<String> params = List.of(
                leagueApiConfig.getDDragonEndpoint(),
                "cdn/img/champion/loading",
                champion+"_"+skin+".jpg"
        );
        return String.join("/",params);
    }

    public String createSplashArtUrlForChampion(String champion) {
        return createSplashArtUrlForChampion(champion,0);
    }

    public String createSplashArtUrlForChampion(String champion, int skin) {
        List<String> params = List.of(
                leagueApiConfig.getDDragonEndpoint(),
                "cdn/img/champion/splash",
                champion+"_"+skin+".jpg"
        );
        return String.join("/",params);
    }

}
