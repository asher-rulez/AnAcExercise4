package com.example.asher.anacexercize4.Networking;

import com.example.asher.anacexercize4.data.MoviesDownloadResult;
import com.example.asher.anacexercize4.data.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IMovieListDownloadService {
    @GET("popular")
    Call<MoviesDownloadResult> GetPopularMovies(@Query("api_key") String api_key);
}
