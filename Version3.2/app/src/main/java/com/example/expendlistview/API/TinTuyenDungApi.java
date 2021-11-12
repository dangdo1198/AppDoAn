package com.example.expendlistview.API;

import com.example.expendlistview.Model.TinTuyenDung;
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

public interface TinTuyenDungApi {
    Gson gson = new GsonBuilder()
            .setDateFormat("")
            .create();
    // Khoi tao retrofit
    TinTuyenDungApi tinTuyenDungApi = new Retrofit.Builder()
            //.baseUrl("http://192.168.1.6:8080/DoAnTotNghiep/")
            .baseUrl("http://192.168.100.3:8080/DoAnTotNghiep/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(TinTuyenDungApi.class);

    // Call api
    @GET("rest/tintuyendungapi")
    Call<List<TinTuyenDung>> allTinTuyenDung();

    @POST("rest/tintuyendungapi")
    Call<TinTuyenDung> addTinTuyenDung(@Body TinTuyenDung taiLieu);

    @PUT("rest/tintuyendungapi")
    Call<TinTuyenDung> updateTinTuyenDung(@Body TinTuyenDung taiLieu);

    @DELETE("rest/tintuyendungapi/{id}")
    Call<TinTuyenDung> deleteTinTuyenDung(@Path("id")int id);

    @GET("rest/tintuyendungapi/{id}")
    Call<TinTuyenDung> getTinTuyenDungById(@Path("id")int id);

}

