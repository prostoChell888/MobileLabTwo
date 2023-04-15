package com.example.lab2.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "episode")
public class Episode {

    @PrimaryKey
    private final int episodeId;
    @ColumnInfo(name = "name")
    private final String name;
    @ColumnInfo(name = "episode")
    private final String episode;
    @ColumnInfo(name = "airDate")
    private final String airDate;

    public Episode(int episodeId, String name, String episode, String airDate) {
        this.episodeId = episodeId;
        this.name = name;
        this.episode = episode;
        this.airDate = airDate;
    }


    public int getEpisodeId() {
        return episodeId;
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
