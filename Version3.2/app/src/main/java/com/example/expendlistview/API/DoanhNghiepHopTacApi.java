package com.example.expendlistview.API;


import com.example.expendlistview.Model.DoanhNghiepHopTac;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DoanhNghiepHopTacApi {
    Gson gson = new GsonBuilder()
            .setDateFormat("")
            .create();
    // Khoi tao retrofit
   DoanhNghiepHopTacApi doanhNghiepHopTacApi = new Retrofit.Builder()
            //.baseUrl("http://192.168.1.6:8080/DoAnTotNghiep/")
            .baseUrl("http://192.168.100.3:8080/DoAnTotNghiep/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(DoanhNghiepHopTacApi.class);

    // Call api
    @GET("rest/doanhnghiepapi")
    Call<List<DoanhNghiepHopTac>> allDoanhNghiep();

    @POST("rest/doanhnghiepapi")
    Call<DoanhNghiepHopTac> addDoanhNghiep(@Body DoanhNghiepHopTac doanhNghiepHopTac);

    @PUT("rest/doanhnghiepapi")
    Call<DoanhNghiepHopTac> updateDoanhNghiep(@Body DoanhNghiepHopTac doanhNghiepHopTac);

    @DELETE("rest/doanhnghiepapi/{id}")
    Call<DoanhNghiepHopTac> deleteDoanhNghiep(@Path("id")int id);

    @GET("rest/doanhnghiepapi/{id}")
    Call<DoanhNghiepHopTac> getDoanhNghiepById(@Path("id")int id);


}
