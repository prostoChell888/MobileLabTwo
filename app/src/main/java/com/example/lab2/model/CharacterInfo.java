package com.example.lab2.model;


public class CharacterInfo {
    private final int id;
    private final String name;
    private final int image;

    private final int[] episode;

    public CharacterInfo(int id, String name, int image, int[] episode) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.episode = episode;
    }

    //    List<Episode> episodes;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int[] getEpisods() {
        return episode;
    }
}
