package com.example.lab2.dto;


public class PersonageDto {
    private final int id;
    private final String name;
    private final String image;
    private final String[] episode;

    public PersonageDto(int id, String name, String image, String[] episode) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.episode = episode;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String[] getEpisods() {
        return episode;
    }
}
