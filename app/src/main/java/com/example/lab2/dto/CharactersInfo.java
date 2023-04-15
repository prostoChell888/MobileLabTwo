package com.example.lab2.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharactersInfo {

    @SerializedName("info")
    private final Info info;
    @SerializedName("results")
    private final List<PersonageDto> charactersInfo;

    public CharactersInfo(Info info, List<PersonageDto> charactersInfo) {
        this.info = info;
        this.charactersInfo = charactersInfo;
    }

    public static class Info {
        @SerializedName("count")
        private final Integer characterCount;
        @SerializedName("pages")
        private final Integer pagesCount;
        @SerializedName("next")
        private final String nextPage;
        @SerializedName("prev")
        private final String prevPage;



        public Info(Integer characterCount, Integer pagesCount, String nextPage, String prevPage) {
            this.characterCount = characterCount;
            this.pagesCount = pagesCount;
            this.nextPage = nextPage;
            this.prevPage = prevPage;
        }
    }

    public Info getInfo() {
        return info;
    }

    public List<PersonageDto> getCharactersInfo() {
        return charactersInfo;
    }

    public Integer getCharacterCount() {
        return info.characterCount;
    }

    public Integer getPagesCount() {
        return info.pagesCount;
    }

    public String getNextPage() {
        return info.nextPage;
    }

    public String getPrevPage() {
        return info.prevPage;
    }
}
