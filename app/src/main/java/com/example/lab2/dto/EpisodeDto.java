package com.example.lab2.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class EpisodeDto {
    @SerializedName("id")
    private final int id;
    @SerializedName("name")
    private final String name;
    @SerializedName("episode")
    private final String episode;
    @SerializedName("air_date")
    private final String airDate;

    public EpisodeDto(int id, String name, String episode, String air_date) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EpisodeDto that = (EpisodeDto) o;
        return id == that.id
                && Objects.equals(name, that.name)
                && Objects.equals(episode, that.episode)
                && Objects.equals(airDate, that.airDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, episode, airDate);
    }
}
