package com.lol.scout.service;

import com.lol.scout.domain.summoner.Summoner;
import com.lol.scout.repository.SummonersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SummonersService {

    private final SummonersRepository summonersRepository;

    public Summoner save(Summoner summoner) {
        return summonersRepository.save(summoner);
    }

}
