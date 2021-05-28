package com.lol.scout.repository;

import com.lol.scout.domain.summoner.ChampionStats;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionStatsRepository extends CrudRepository<ChampionStats,String> {

}
