package com.example.asher.anacexercize4.Networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    private static Retrofit retrofit = new Retrofit.Builder().
            baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static IMovieListDownloadService moviesService = retrofit.create(IMovieListDownloadService.class);

}
