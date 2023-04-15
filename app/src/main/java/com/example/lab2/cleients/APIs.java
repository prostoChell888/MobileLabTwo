package com.example.lab2.cleients;

import com.example.lab2.dto.CharactersInfo;
import com.example.lab2.dto.EpisodeDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIs {
    String BASE_URL = "https://rickandmortyapi.com/api/";

    @GET("episode/{episodes}/")
    Call<List<EpisodeDto>> getEpisodes(@Path("episodes") String episodes);

    @GET("character")
    Call<CharactersInfo> getCharacters( @Query("page") String page);

    @GET("character")
    Call<CharactersInfo> getCharacters();

}
