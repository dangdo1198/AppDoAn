package com.example.expendlistview.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.expendlistview.API.DeTaiNghienCuuApi;
import com.example.expendlistview.Adapter.DeTaiBiHuyAdapter;
import com.example.expendlistview.Adapter.DeTaiDaDangKyAdapter;
import com.example.expendlistview.Adapter.DeTaiDuocDuyetAdapter;
import com.example.expendlistview.Model.DeTaiNghienCuu;
import com.example.expendlistview.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeTaiDuocDuyet extends AppCompatActivity {
    RecyclerView recyclerView;
    List<DeTaiNghienCuu> listDeTaiNghienCuu;
    DeTaiDuocDuyetAdapter deTaiDuocDuyetAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_tai_duoc_duyet);
        recyclerView = findViewById(R.id.recyclerView);
        clickCallApi();
    }

    public void clickCallApi() {
        DeTaiNghienCuuApi.deTaiNghienCuuApi.deTaiDuyet().enqueue(new Callback<List<DeTaiNghienCuu>>() {
            @Override
            public void onResponse(Call<List<DeTaiNghienCuu>> call, Response<List<DeTaiNghienCuu>> response) {
                listDeTaiNghienCuu = response.body();
                deTaiDuocDuyetAdapter = new DeTaiDuocDuyetAdapter(DeTaiDuocDuyet.this,DeTaiDuocDuyet.this,listDeTaiNghienCuu);
                recyclerView.setAdapter(deTaiDuocDuyetAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(DeTaiDuocDuyet.this));
            }

            @Override
            public void onFailure(Call<List<DeTaiNghienCuu>> call, Throwable t) {
                Toast.makeText(DeTaiDuocDuyet.this,"false",Toast.LENGTH_SHORT).show();

            }
        });

    }

}


