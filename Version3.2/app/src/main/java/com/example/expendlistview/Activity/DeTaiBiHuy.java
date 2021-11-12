package com.example.expendlistview.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.expendlistview.API.DeTaiNghienCuuApi;
import com.example.expendlistview.Adapter.DeTaiBiHuyAdapter;
import com.example.expendlistview.Adapter.DeTaiDaDangKyAdapter;
import com.example.expendlistview.Model.DeTaiNghienCuu;
import com.example.expendlistview.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeTaiBiHuy extends AppCompatActivity {
    RecyclerView recyclerView;
    List<DeTaiNghienCuu> listDeTaiNghienCuu;
    DeTaiBiHuyAdapter deTaiBiHuyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_tai_bi_huy);
        recyclerView = findViewById(R.id.recyclerView);
        clickCallApi();
    }

    public void clickCallApi() {
        DeTaiNghienCuuApi.deTaiNghienCuuApi.deTaiHuy().enqueue(new Callback<List<DeTaiNghienCuu>>() {
            @Override
            public void onResponse(Call<List<DeTaiNghienCuu>> call, Response<List<DeTaiNghienCuu>> response) {
                listDeTaiNghienCuu = response.body();
                deTaiBiHuyAdapter = new DeTaiBiHuyAdapter(DeTaiBiHuy.this,DeTaiBiHuy.this,listDeTaiNghienCuu);
                recyclerView.setAdapter(deTaiBiHuyAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(DeTaiBiHuy.this));
            }

            @Override
            public void onFailure(Call<List<DeTaiNghienCuu>> call, Throwable t) {
                Toast.makeText(DeTaiBiHuy.this,"false",Toast.LENGTH_SHORT).show();

            }
        });

    }

}


