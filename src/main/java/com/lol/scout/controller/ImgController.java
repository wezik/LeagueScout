package com.lol.scout.controller;

import com.lol.scout.manager.ImgManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("v1/img")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ImgController {

    private final ImgManager imgManager;

    @GetMapping(value = "rank/{rank}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getRankImage(@PathVariable String rank) throws IOException {
        BufferedImage bi = imgManager.getRank(rank).orElseThrow(IOException::new);
        return convertBufferedImageToByteArray(bi);
    }

    @GetMapping(value = "rank/{rank}/{position}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getRankPosition(@PathVariable String rank, @PathVariable String position) throws IOException {
        BufferedImage bi = imgManager.getPositionRank(rank,position).orElseThrow(IOException::new);
        return convertBufferedImageToByteArray(bi);
    }

    private byte[] convertBufferedImageToByteArray(BufferedImage bi) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi,"png",baos);
        baos.flush();
        byte[] result = baos.toByteArray();
        baos.close();
        return result;
    }

}
