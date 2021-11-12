package com.example.expendlistview.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.expendlistview.API.DeTaiNghienCuuApi;
import com.example.expendlistview.Adapter.DeTaiDaDangKyAdapter;
import com.example.expendlistview.Adapter.PheDuyetDeTaiAdapter;
import com.example.expendlistview.Model.DeTaiNghienCuu;
import com.example.expendlistview.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeTaiDaDangKy extends AppCompatActivity {
    RecyclerView recyclerView;
    List<DeTaiNghienCuu> listDeTaiNghienCuu;
    DeTaiDaDangKyAdapter deTaiDaDangKyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_tai_da_dang_ky);
        recyclerView = findViewById(R.id.recyclerView);
        clickCallApi();
    }

    public void clickCallApi() {
        DeTaiNghienCuuApi.deTaiNghienCuuApi.allDeTai().enqueue(new Callback<List<DeTaiNghienCuu>>() {
            @Override
            public void onResponse(Call<List<DeTaiNghienCuu>> call, Response<List<DeTaiNghienCuu>> response) {
                listDeTaiNghienCuu = response.body();
                deTaiDaDangKyAdapter = new DeTaiDaDangKyAdapter(DeTaiDaDangKy.this,DeTaiDaDangKy.this,listDeTaiNghienCuu);
                recyclerView.setAdapter(deTaiDaDangKyAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(DeTaiDaDangKy.this));
            }

            @Override
            public void onFailure(Call<List<DeTaiNghienCuu>> call, Throwable t) {
                Toast.makeText(DeTaiDaDangKy.this,"false",Toast.LENGTH_SHORT).show();

            }
        });

    }
}