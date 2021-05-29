package com.lol.scout.repository;

import com.lol.scout.domain.summoner.Summoner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SummonersRepository extends CrudRepository<Summoner,String> {
    List<Summoner> findByName(String name);
    Optional<Summoner> findByPuuid(String puuid);
}
