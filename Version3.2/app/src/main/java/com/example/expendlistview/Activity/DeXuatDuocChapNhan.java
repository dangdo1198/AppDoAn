package com.example.expendlistview.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.expendlistview.API.ChuongTrinhLienKetApi;
import com.example.expendlistview.Adapter.DeXuatChoPhanHoiAdapter;
import com.example.expendlistview.Adapter.DeXuatDongYAdapter;
import com.example.expendlistview.Model.CTLKDoanhNghiep;
import com.example.expendlistview.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeXuatDuocChapNhan extends AppCompatActivity {
    Spinner spinner;
    int itemHienTai =0;
    RecyclerView recyclerView;
    List<CTLKDoanhNghiep> listCtlkDoanhNghiep;
    DeXuatDongYAdapter deXuatDongYAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_xuat_duoc_chap_nhan);
        recyclerView = findViewById(R.id.recyclerView);
        spinner = findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(itemHienTai==position){
                    return;
                }
                if(parent.getItemAtPosition(position).equals("Đề xuất chờ phản hồi")){
                    Intent intent = new Intent(getBaseContext(),DeXuatChuaCoPhanHoi.class);
                    startActivity(intent);
                }
                if(parent.getItemAtPosition(position).equals("Đề xuất bị từ chối")){
                    Intent intent = new Intent(getBaseContext(),DeXuatBiTuChoi.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        clickCallApi();
    }
    public void clickCallApi(){
        ChuongTrinhLienKetApi.chuongTrinhLienKetApi.chuongTrinhDongY().enqueue(new Callback<List<CTLKDoanhNghiep>>() {
            @Override
            public void onResponse(Call<List<CTLKDoanhNghiep>> call, Response<List<CTLKDoanhNghiep>> response) {
                listCtlkDoanhNghiep = response.body();
                deXuatDongYAdapter = new DeXuatDongYAdapter(DeXuatDuocChapNhan.this,DeXuatDuocChapNhan.this,listCtlkDoanhNghiep);
                recyclerView.setAdapter(deXuatDongYAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(DeXuatDuocChapNhan.this));
            }

            @Override
            public void onFailure(Call<List<CTLKDoanhNghiep>> call, Throwable t) {

            }
        });
    }
}