package com.lol.scout.facade;

import com.lol.scout.manager.AssetUrlManager;
import com.lol.scout.manager.ImgManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ImgFacade {

    private final ImgManager imgManager;
    private final AssetUrlManager assetUrlManager;

    public String getChampionIconUrl(String id) {
        return assetUrlManager.createIconArtUrlForChampion(id);
    }

    public String getSummonerIconUrl(String id) {
        return assetUrlManager.createIconArtUrlForSummoner(id);
    }

    public String getSummonerSpellIconUrl(String id) {
        return assetUrlManager.createIconArtUrlForSummonerSpell(id);
    }

    public String getItemIconUrl(String id) {
        return assetUrlManager.createIconArtUrlForItem(id);
    }

    public String getChampionLoadingArtUrl(String id) {
        return assetUrlManager.createLoadingArtUrlForChampion(id);
    }

    public String getChampionLoadingArtUrl(String id, int skin) {
        return assetUrlManager.createLoadingArtUrlForChampion(id,skin);
    }

    public String getChampionSplashArtUrl(String id) {
        return assetUrlManager.createSplashArtUrlForChampion(id);
    }

    public String getChampionSplashArtUrl(String id, int skin) {
        return assetUrlManager.createSplashArtUrlForChampion(id,skin);
    }

    public Optional<BufferedImage> getRankImage(String rank) throws IOException {
        return imgManager.getRank(rank);
    }

    public Optional<BufferedImage> getRankPositionImage(String rank, String position) throws IOException {
        return imgManager.getPositionRank(rank,position);
    }

}
