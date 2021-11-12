package com.example.expendlistview.API;

import com.example.expendlistview.Model.TaiLieuNghienCuu;
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

public interface TaiLieuNghienCuuApi {
    Gson gson = new GsonBuilder()
            .setDateFormat("")
            .create();
    // Khoi tao retrofit
    TaiLieuNghienCuuApi taiLieuNghienCuuApi = new Retrofit.Builder()
            //.baseUrl("http://192.168.1.6:8080/DoAnTotNghiep/")
            .baseUrl("http://192.168.100.3:8080/DoAnTotNghiep/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(TaiLieuNghienCuuApi.class);

    // Call api
    @GET("rest/tailieuapi")
    Call<List<TaiLieuNghienCuu>> allTaiLieu();

    @POST("rest/tailieuapi")
    Call<TaiLieuNghienCuu> addTaiLieu(@Body TaiLieuNghienCuu taiLieu);

    @PUT("rest/tailieuapi")
    Call<TaiLieuNghienCuu> updateTaiLieu(@Body TaiLieuNghienCuu taiLieu);

    @DELETE("rest/tailieuapi/{id}")
    Call<TaiLieuNghienCuu> deleteTaiLieu(@Path("id")int id);

    @GET("rest/tailieuapi/{id}")
    Call<TaiLieuNghienCuu> getTaiLieuById(@Path("id")int id);



}
