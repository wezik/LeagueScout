package com.lol.scout.manager;

import com.lol.scout.config.CoreConfig;
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

}
