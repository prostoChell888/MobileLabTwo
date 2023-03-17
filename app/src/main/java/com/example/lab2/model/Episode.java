package com.example.lab2.model;

public class Episode {
    private final int id;
    private final String name;
    private final String episode;
    private final String airDate;

    public Episode(int id, String name, String episode, String air_date) {
        this.id = id;
        this.name = name;
        this.episode = episode;
        this.airDate = air_date;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEpisode() {
        return episode;
    }

    public String getAirDate() {
        return airDate;
    }
}
