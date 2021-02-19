package com.theo.videogameqizz;


import retrofit2.Call;
import retrofit2.http.GET;

public interface VideoGameQizzService {
    @GET("videogameqizz/")
    Call<String> load();

}
