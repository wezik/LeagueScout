package com.lol.scout.domain.currentgame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannedChampion {
    private int pickTurn;
    private long championId;
    private long teamId;
}
