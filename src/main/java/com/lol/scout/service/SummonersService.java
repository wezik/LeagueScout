package com.lol.scout.service;

import com.lol.scout.domain.summoner.Summoner;
import com.lol.scout.repository.SummonersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SummonersService {

    private final SummonersRepository summonersRepository;

    public Optional<Summoner> getByPuuid(String puuid) {
        return summonersRepository.findByPuuid(puuid);
    }

    public List<Summoner> getByName(String name) {
        return summonersRepository.findByName(name);
    }

    public Summoner save(Summoner summoner) {
        return summonersRepository.save(summoner);
    }

}
