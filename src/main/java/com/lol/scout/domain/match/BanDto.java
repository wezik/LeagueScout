package com.lol.scout.domain.match;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BanDto {
    private int championId;
    private int pickTurn;
}
