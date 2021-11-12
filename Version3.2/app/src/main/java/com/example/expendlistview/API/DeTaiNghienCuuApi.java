package com.example.expendlistview.API;

import com.example.expendlistview.Model.DeTaiNghienCuu;
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

public interface DeTaiNghienCuuApi {
    Gson gson = new GsonBuilder()
            .setDateFormat("")
            .create();
    // Khoi tao retrofit
    DeTaiNghienCuuApi deTaiNghienCuuApi = new Retrofit.Builder()
            //.baseUrl("http://192.168.1.6:8080/DoAnTotNghiep/")
            .baseUrl("http://192.168.100.3:8080/DoAnTotNghiep/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(DeTaiNghienCuuApi.class);

    // Call api
    @GET("rest/detaiapi")
    Call<List<DeTaiNghienCuu>> allDeTai();

    @POST("rest/detaiapi")
    Call<DeTaiNghienCuu> addDeTai(@Body DeTaiNghienCuu deTaiNghienCuu);

    @PUT("rest/detaiapi")
    Call<DeTaiNghienCuu> pheDuyetDeTai(@Body DeTaiNghienCuu deTaiNghienCuu);

    @DELETE("rest/detaiapi/{id}")
    Call<DeTaiNghienCuu> deleteDeTai(@Path("id")int id);

    @GET("rest/detaiapi/{id}")
    Call<DeTaiNghienCuu> getDeTaiById(@Path("id")int id);

    @GET("rest/detaiapi/detaiduyet")
    Call<List<DeTaiNghienCuu>> deTaiDuyet();

    @GET("rest/detaiapi/detaihuy")
    Call<List<DeTaiNghienCuu>> deTaiHuy();

}
