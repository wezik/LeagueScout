package com.lol.scout.domain.league;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MiniSeries {
    private int losses;
    private String progress;
    private int target;
    private int wins;
}
