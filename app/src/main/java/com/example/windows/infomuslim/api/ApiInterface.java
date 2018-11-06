package com.example.windows.infomuslim.api;

import com.example.windows.infomuslim.Model.HeroesItem;
import com.example.windows.infomuslim.Model.MasjidModel;
import com.example.windows.infomuslim.Model.ModelSlider;
import com.example.windows.infomuslim.Model.ResponseDataPenceramah;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("masjid")
    Call<ArrayList<MasjidModel>> getMovies();

    @GET("penceramah")
    Call<ArrayList<ResponseDataPenceramah>> getPenceramah();

    @GET("heroes.php")
    Call<ModelSlider> getHeroes();
}
//penceramah

