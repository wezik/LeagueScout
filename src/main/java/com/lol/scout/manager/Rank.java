package com.lol.scout.manager;

public enum Rank {

    IRON("Iron"),
    BRONZE("Bronze"),
    SILVER("Silver"),
    GOLD("Gold"),
    PLATINUM("Platinum"),
    DIAMOND("Diamond"),
    MASTER("Master"),
    GRANDMASTER("Grandmaster"),
    CHALLENGER("Challenger");

    private final static String directory = "/ranks/";
    private final String name;

    Rank(String name) {
        this.name=name;
    }

    public String getPath() {
        return directory+name+".png";
    }

    public String getName() {
        return name;
    }

}
