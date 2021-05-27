package com.lol.scout.controller;

import com.lol.scout.domain.champion.ChampionDto;
import com.lol.scout.exception.ApiFetchFailedException;
import com.lol.scout.facade.DataCacheFacade;
import com.lol.scout.manager.ImgManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("v1/img")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ImgController {

    private final ImgManager imgManager;
    private final DataCacheFacade dataCacheFacade;

    @GetMapping(value = "icon/item/{id}")
    public ModelAndView redirectToItemIcon(@PathVariable String id) {
        String url = imgManager.createIconArtUrlForItem(id);
        return new ModelAndView("redirect:" + url);
    }

    @GetMapping(value = "icon/summoner/{id}")
    public ModelAndView redirectToSummonerIcon(@PathVariable String id) {
        String url = imgManager.createIconArtUrlForSummoner(id);
        return new ModelAndView("redirect:" + url);
    }

    @GetMapping(value = "icon/summonerspell/{id}")
    public ModelAndView redirectToIconForSummonerSpell(@PathVariable String id) {
        String url = imgManager.createIconArtUrlForSummonerSpell(id);
        return new ModelAndView("redirect:" + url);
    }

    @GetMapping(value = "icon/champion/{id}")
    public ModelAndView redirectToIconForChampion(@PathVariable String id) throws ApiFetchFailedException {
        Map<String,ChampionDto> map = dataCacheFacade.getChampions().getData();
        String url = imgManager.createIconArtUrlForChampion(map.get(id).getId());
        return new ModelAndView("redirect:" + url);
    }

    @GetMapping(value = "loading/{id}")
    public ModelAndView redirectToLoadingArt(@PathVariable String id) throws ApiFetchFailedException {
        ChampionDto championDto = dataCacheFacade.getChampions().getData().get(id);
        String url = imgManager.createLoadingArtUrlForChampion(championDto.getId());
        return new ModelAndView("redirect:"+url);
    }

    @GetMapping(value = "loading/{id}/{skin_id}")
    public ModelAndView redirectToLoadingArtWithSkin(@PathVariable String id, @PathVariable int skin_id) throws ApiFetchFailedException {
        ChampionDto championDto = dataCacheFacade.getChampions().getData().get(id);
        String url = imgManager.createLoadingArtUrlForChampion(championDto.getId(),skin_id);
        return new ModelAndView("redirect:"+url);
    }

    @GetMapping(value = "splash/{id}")
    public ModelAndView redirectToSplashArt(@PathVariable String id) throws ApiFetchFailedException {
        ChampionDto championDto = dataCacheFacade.getChampions().getData().get(id);
        String url = imgManager.createSplashArtUrlForChampion(championDto.getId());
        return new ModelAndView("redirect:"+url);
    }

    @GetMapping(value = "splash/{id}/{skin_id}")
    public ModelAndView redirectToSplashArtWithSkin(@PathVariable String id, @PathVariable int skin_id) throws ApiFetchFailedException {
        ChampionDto championDto = dataCacheFacade.getChampions().getData().get(id);
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
