package com.lol.scout.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImgDto {
    private String full;
    private String sprite;
    private String group;
    private int x;
    private int y;
    private int w;
    private int h;
}
