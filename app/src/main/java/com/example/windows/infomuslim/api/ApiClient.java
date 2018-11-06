package com.example.windows.infomuslim.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
//    String url = "http://muslim-info.xakti.tech/api/masjid";
//    String url = "http://muslim-info.xakti.tech/api/penceramah";
    public static String BASE_URL ="http://muslim-info.xakti.tech/api/";
    private static Retrofit retrofit;
    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
