package com.example.lab2.db.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "personage")
public class Personage {
    public Personage(int characterId,
                     String name,
                     String image,
                     String episodes) {
        this.characterId = characterId;
        this.name = name;
        this.image = image;
        this.episodes = episodes;
    }

    @PrimaryKey
    private final int characterId;
    @ColumnInfo(name = "name")
    private final String name;
    @ColumnInfo(name = "image")
    private final String image;

    @ColumnInfo(name = "episodes")
    private final String episodes;

    public int getCharacterId() {
        return characterId;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getEpisodes() {
        return episodes;
    }
}
