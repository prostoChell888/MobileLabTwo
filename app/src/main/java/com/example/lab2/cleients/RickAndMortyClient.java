package com.example.lab2.cleients;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RickAndMortyClient {
    private static RickAndMortyClient instance;
    private final APIs apis;

    private RickAndMortyClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIs.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apis = retrofit.create(APIs.class);
    }

    public static synchronized RickAndMortyClient getInstance() {
        if (instance == null) {
            instance = new RickAndMortyClient();
        }
        return instance;
    }

    public  APIs getApis() {
        return apis;
    }

}
