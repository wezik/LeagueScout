package com.lol.scout.manager;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class ImgManager {

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

    public Optional<BufferedImage> getRank(String string) throws IOException {
        for (Rank rank : ranks) {
            if (rank.getName().equalsIgnoreCase(string)) {
                return Optional.ofNullable(ImageIO.read(new File(rank.getPath())));
            }
        }
        return Optional.empty();
    }

}
