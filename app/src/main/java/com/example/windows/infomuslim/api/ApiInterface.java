package com.example.windows.infomuslim.api;

import com.example.windows.infomuslim.Model.MasjidModel;
import com.example.windows.infomuslim.Model.ModelDataHeader;
import com.example.windows.infomuslim.Model.ModelDataPanti;
import com.example.windows.infomuslim.Model.ModelPostingan;
import com.example.windows.infomuslim.Model.ModelSlider;
import com.example.windows.infomuslim.Model.ResponseDataPenceramah;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;


//http://muslim-info.xakti.tech/api/postingan/
public interface ApiInterface {
    @GET("masjid")
    Call<ArrayList<MasjidModel>> getMovies();

    @GET("penceramah")
    Call<ArrayList<ResponseDataPenceramah>> getPenceramah();

    @GET("heroes.php")
    Call<ModelSlider> getHeroes();

    @GET("postingan")
    Call<ArrayList<ModelPostingan>> getPostingan();

    @GET("panti-asuhan")
    Call<ArrayList<ModelDataPanti>> getDataPanti();

    @GET("slide-header")
    Call<ArrayList<ModelDataHeader>> getDataHeader();
}
//penceramah

