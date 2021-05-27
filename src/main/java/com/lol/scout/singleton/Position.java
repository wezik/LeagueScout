package com.lol.scout.singleton;

public enum Position {

    SUPPORT("Support"),
    BOT("Bot"),
    MID("Mid"),
    JUNGLE("Jungle"),
    TOP("Top");

    private final static String directory = "/rankedpositions/";
    private final String name;

    Position(String name) {
        this.name=name;
    }

    public String getPath(Rank rank) {
        return directory+"Position_"+rank.getName()+'-'+name+".png";
    }

    public String getName() {
        return name;
    }

}
