package com.lol.scout.controller;

import com.lol.scout.api.league.config.LeagueApiConfig;
import com.lol.scout.domain.champion.ChampionListDto;
import com.lol.scout.domain.summoners.SummonerSpellDetails;
import com.lol.scout.domain.summoners.SummonerSpellsDto;
import com.lol.scout.exception.ApiFetchFailedException;
import com.lol.scout.exception.SummonerSpellNotFoundException;
import com.lol.scout.facade.ChampionFacade;
import com.lol.scout.facade.DataFacade;
import com.lol.scout.facade.ImgFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DataBufferFactory;
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

    private final ImgFacade imgFacade;
    private final ChampionFacade championFacade;
    private final LeagueApiConfig leagueApiConfig;
    private final DataFacade dataFacade;

    @GetMapping(value = "icon/item/{id}")
    public ModelAndView redirectToItemIcon(@PathVariable String id) {
        String url = imgFacade.getItemIconUrl(id);
        return new ModelAndView("redirect:" + url);
    }

    @GetMapping(value = "icon/summoner/{id}")
    public ModelAndView redirectToSummonerIcon(@PathVariable String id) {
        String url = imgFacade.getSummonerIconUrl(id);
        return new ModelAndView("redirect:" + url);
    }

    @GetMapping(value = "icon/summonerspell/{key}")
    public ModelAndView redirectToIconForSummonerSpell(@PathVariable String key) throws ApiFetchFailedException, SummonerSpellNotFoundException {
        SummonerSpellsDto ssDto = dataFacade.getSummonerSpells().orElseThrow(ApiFetchFailedException::new);
        SummonerSpellDetails ssDetails = ssDto.getData().values().stream()
                .filter(e -> e.getKey().equalsIgnoreCase(key))
                .findFirst()
                .orElseThrow(SummonerSpellNotFoundException::new);
        String url = imgFacade.getSummonerSpellIconUrl(ssDetails.getId());
        return new ModelAndView("redirect:" + url);
    }

    @GetMapping(value = "icon/champion/{id}")
    public ModelAndView redirectToIconForChampion(@PathVariable String id) throws ApiFetchFailedException {
        ChampionListDto list = championFacade.getChampions(leagueApiConfig.getVersion()).orElseThrow(ApiFetchFailedException::new);
        String url = imgFacade.getChampionIconUrl(list.getData().get(id).getId());
        return new ModelAndView("redirect:" + url);
    }

    @GetMapping(value = "loading/{id}")
    public ModelAndView redirectToLoadingArt(@PathVariable String id) throws ApiFetchFailedException {
        ChampionListDto list = championFacade.getChampions(leagueApiConfig.getVersion()).orElseThrow(ApiFetchFailedException::new);
        String url = imgFacade.getChampionLoadingArtUrl(list.getData().get(id).getId());
        return new ModelAndView("redirect:"+url);
    }

    @GetMapping(value = "loading/{id}/{skin_id}")
    public ModelAndView redirectToLoadingArtWithSkin(@PathVariable String id, @PathVariable int skin_id) throws ApiFetchFailedException {
        ChampionListDto list = championFacade.getChampions(leagueApiConfig.getVersion()).orElseThrow(ApiFetchFailedException::new);
        String url = imgFacade.getChampionLoadingArtUrl(list.getData().get(id).getId(),skin_id);
        return new ModelAndView("redirect:"+url);
    }

    @GetMapping(value = "splash/{id}")
    public ModelAndView redirectToSplashArt(@PathVariable String id) throws ApiFetchFailedException {
        ChampionListDto list = championFacade.getChampions(leagueApiConfig.getVersion()).orElseThrow(ApiFetchFailedException::new);
        String url = imgFacade.getChampionSplashArtUrl(list.getData().get(id).getId());
        return new ModelAndView("redirect:"+url);
    }

    @GetMapping(value = "splash/{id}/{skin_id}")
    public ModelAndView redirectToSplashArtWithSkin(@PathVariable String id, @PathVariable int skin_id) throws ApiFetchFailedException {
        ChampionListDto list = championFacade.getChampions(leagueApiConfig.getVersion()).orElseThrow(ApiFetchFailedException::new);
        String url = imgFacade.getChampionSplashArtUrl(list.getData().get(id).getId(),skin_id);
        return new ModelAndView("redirect:"+url);
    }

    @GetMapping(value = "rank/{rank}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getRankImage(@PathVariable String rank) throws IOException {
        BufferedImage bi = imgFacade.getRankImage(rank).orElseThrow(IOException::new);
        return convertBufferedImageToByteArray(bi);
    }

    @GetMapping(value = "rank/{rank}/{position}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getRankPosition(@PathVariable String rank, @PathVariable String position) throws IOException {
        BufferedImage bi = imgFacade.getRankPositionImage(rank,position).orElseThrow(IOException::new);
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
