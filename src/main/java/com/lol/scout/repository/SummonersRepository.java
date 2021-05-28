package com.lol.scout.repository;

import com.lol.scout.domain.summoner.Summoner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummonersRepository extends CrudRepository<Summoner,String> {
}
