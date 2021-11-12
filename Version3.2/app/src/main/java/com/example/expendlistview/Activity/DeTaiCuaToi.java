package com.example.expendlistview.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.expendlistview.R;

public class DeTaiCuaToi extends AppCompatActivity {
    Button dsDeTaiDaDangKyBtn, deTaiDuyetBtn, detTaiHuyBtn, dangKyDeTaiMoiBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_tai_cua_toi);
        dsDeTaiDaDangKyBtn = findViewById(R.id.dsdetai_btn);
        deTaiDuyetBtn =findViewById(R.id.detaiduyet_btn);
        detTaiHuyBtn = findViewById(R.id.detaihuy_btn);
        dangKyDeTaiMoiBtn = findViewById(R.id.dangkydetaimoi_btn);
        dsDeTaiDaDangKyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), DeTaiDaDangKy.class);
                startActivity(intent);

            }
        });

        deTaiDuyetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), DeTaiDuocDuyet.class);
                startActivity(intent);

            }
        });
        detTaiHuyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), DeTaiBiHuy.class);
                startActivity(intent);

            }
        });
        dangKyDeTaiMoiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), DangKyDeTai.class);
                startActivity(intent);
            }
        });
    }
}