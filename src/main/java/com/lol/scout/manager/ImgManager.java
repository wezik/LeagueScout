package com.lol.scout.manager;

import com.lol.scout.api.league.config.LeagueApiConfig;
import com.lol.scout.config.CoreConfig;
import com.lol.scout.singleton.Position;
import com.lol.scout.singleton.Rank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ImgManager {

    private final CoreConfig coreConfig;
    private final LeagueApiConfig leagueApiConfig;

    private final List<Rank> ranks = List.of(
            Rank.IRON,
            Rank.BRONZE,
            Rank.SILVER,
            Rank.GOLD,
            Rank.PLATINUM,
            Rank.DIAMOND,
            Rank.MASTER,
            Rank.GRANDMASTER,
            Rank.CHALLENGER
    );

    private final List<Position> positions = List.of(
            Position.SUPPORT,
            Position.BOT,
            Position.MID,
            Position.JUNGLE,
            Position.TOP
    );

    public Optional<BufferedImage> getRank(String rank) throws IOException {
        for (Rank r : ranks) {
            if (r.getName().equalsIgnoreCase(rank)) {
                return Optional.ofNullable(ImageIO.read(new File(coreConfig.getImagesDirectory()+r.getPath())));
            }
        }
        return Optional.empty();
    }

    public Optional<BufferedImage> getPositionRank(String rank, String position) throws IOException {
        for (Rank r : ranks) {
            if (r.getName().equalsIgnoreCase(rank)) {
                for (Position p : positions) {
                    if (p.getName().equalsIgnoreCase(position)) {
                        return Optional.ofNullable(ImageIO.read(new File(coreConfig.getImagesDirectory()+p.getPath(r))));
                    }
                }
            }
        }
        return Optional.empty();
    }

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
