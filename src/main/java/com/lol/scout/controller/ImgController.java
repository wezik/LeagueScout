package com.lol.scout.controller;

import com.lol.scout.domain.ChampionDto;
import com.lol.scout.exception.ApiFetchFailedException;
import com.lol.scout.facade.CacheFacade;
import com.lol.scout.manager.ImgManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    private final CacheFacade cacheFacade;

    @GetMapping(value = "icon/{champion}")
    public ModelAndView getIconArtUrl(@PathVariable String champion) throws ApiFetchFailedException {
        ChampionDto championDto = cacheFacade.getChampions().getData().get(champion);
        String url = imgManager.createIconArtUrlForChampion(championDto.getId());
        return new ModelAndView("redirect:"+url);
    }

    @GetMapping(value = "loading/{champion}")
    public ModelAndView getLoadingArtUrl(@PathVariable String champion) throws ApiFetchFailedException {
        ChampionDto championDto = cacheFacade.getChampions().getData().get(champion);
        String url = imgManager.createLoadingArtUrlForChampion(championDto.getId());
        return new ModelAndView("redirect:"+url);
    }

    @GetMapping(value = "loading/{champion}/{skin_id}")
    public ModelAndView getLoadingArtUrlWithSkin(@PathVariable String champion, @PathVariable int skin_id) throws ApiFetchFailedException {
        ChampionDto championDto = cacheFacade.getChampions().getData().get(champion);
        String url = imgManager.createLoadingArtUrlForChampion(championDto.getId(),skin_id);
        return new ModelAndView("redirect:"+url);
    }

    @GetMapping(value = "splash/{champion}")
    public ModelAndView getSplashArtUrl(@PathVariable String champion) throws ApiFetchFailedException {
        ChampionDto championDto = cacheFacade.getChampions().getData().get(champion);
        String url = imgManager.createSplashArtUrlForChampion(championDto.getId());
        return new ModelAndView("redirect:"+url);
    }

    @GetMapping(value = "splash/{champion}/{skin_id}")
    public ModelAndView getSplashArtUrlWithSkin(@PathVariable String champion, @PathVariable int skin_id) throws ApiFetchFailedException {
        ChampionDto championDto = cacheFacade.getChampions().getData().get(champion);
        String url = imgManager.createSplashArtUrlForChampion(championDto.getId(),skin_id);
        return new ModelAndView("redirect:"+url);
    }

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
