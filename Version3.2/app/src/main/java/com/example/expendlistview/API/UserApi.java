package com.example.expendlistview.API;

import com.example.expendlistview.Model.CTLKDoanhNghiep;
import com.example.expendlistview.Model.User;
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

public interface UserApi {
    Gson gson = new GsonBuilder()
            .setDateFormat("")
            .create();
    // Khoi tao retrofit

            UserApi userApi = new Retrofit.Builder()
            //.baseUrl("http://192.168.1.6:8080/DoAnTotNghiep/")
            .baseUrl("http://192.168.100.3:8080/DoAnTotNghiep/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(UserApi.class);

    // Call api
    @GET("rest/userapi")
    Call<List<User>> allUser();

//    @POST("rest/chuongtrinhlienketapi")
//    Call<CTLKDoanhNghiep> guiDeXuat(@Body CTLKDoanhNghiep ctlkDoanhNghiep);
//
//    @PUT("rest/chuongtrinhlienketapi")
//    Call<CTLKDoanhNghiep> phanHoiDeXuat(@Body CTLKDoanhNghiep ctlkDoanhNghiep);
//
//    @DELETE("rest/chuongtrinhlienketapi/{id}")
//    Call<CTLKDoanhNghiep> xoaDeXuat(@Path("id")int id);
//
//    @GET("rest/chuongtrinhlienketapi/{id}")
//    Call<CTLKDoanhNghiep> getDeXuatById(@Path("id")int id);
//
//    @GET("rest/chuongtrinhlienketapi/chuongtrinhdongy")
//    Call<List<CTLKDoanhNghiep>> chuongTrinhDongY();
//
//    @GET("rest/chuongtrinhlienketapi/chuongtrinhtuchoi")
//    Call<List<CTLKDoanhNghiep>> chuongTrinhTuChoi();
//    @GET("rest/chuongtrinhlienketapi/chuongtrinhchophanhoi")
//    Call<List<CTLKDoanhNghiep>> chuongTrinhChoPhanHoi();
}
