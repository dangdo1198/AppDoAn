package com.example.expendlistview.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.expendlistview.API.TinTuyenDungApi;
import com.example.expendlistview.Model.TinTuyenDung;
import com.example.expendlistview.R;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XemChiTietTinTuyenDung extends AppCompatActivity {
    TextView idTxt, tieuDeTxt, tenDoanhNghiepTxt, noiDungTxt, soDienThoaiTxt, emailTxt, websiteTxt, nguoiDangTxt,chucVuTxt;
    TinTuyenDung tinTuyenDung;
    ScrollView scrollView;
    ImageView quayLaiImg, capNhatImg;
    int idTinTuyenDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_chi_tiet_tin_tuyen_dung);
        idTxt= findViewById(R.id.id_txt);
        tieuDeTxt = findViewById(R.id.tieude_txt);
        tenDoanhNghiepTxt = findViewById(R.id.tendoanhnghiep_txt);
        noiDungTxt = findViewById(R.id.noidung_txt);
        soDienThoaiTxt = findViewById(R.id.sodienthoai_txt);
        emailTxt = findViewById(R.id.email_txt);
        websiteTxt = findViewById(R.id.website_txt);
        nguoiDangTxt = findViewById(R.id.nguoidang_txt);
        chucVuTxt = findViewById(R.id.chucvu_txt);
        scrollView = findViewById(R.id.scrollView1);
        quayLaiImg = findViewById(R.id.quaylai_img);
        capNhatImg = findViewById(R.id.capnhat_img);

        Intent intent = getIntent();
        tinTuyenDung =(TinTuyenDung)intent.getExtras().getSerializable("xemchitiettintuyendung");
        tieuDeTxt.setText(tinTuyenDung.getTieuDe());
        tenDoanhNghiepTxt.setText(tinTuyenDung.getTenDoanhNghiep());
        noiDungTxt.setText(tinTuyenDung.getNoiDung());
        soDienThoaiTxt.setText(tinTuyenDung.getSoDienThoai());
        emailTxt.setText(tinTuyenDung.getEmail());
        websiteTxt.setText(tinTuyenDung.getWebsite());
        nguoiDangTxt.setText(tinTuyenDung.getNguoiDang());
        chucVuTxt.setText(tinTuyenDung.getChucVu());
        idTxt.setText(String.valueOf(tinTuyenDung.getId()));
        websiteTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(String.valueOf(tinTuyenDung.getWebsite())));
                startActivity(intent);

            }
        });

        quayLaiImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), DangTinTuyenDung.class);
                startActivity(intent);


            }
        });
        capNhatImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idTinTuyenDung = Integer.parseInt((String) idTxt.getText());
                getTinTuyenDungById(idTinTuyenDung);

            }
        });

    }
    public void  getTinTuyenDungById(int id){
        TinTuyenDungApi.tinTuyenDungApi.getTinTuyenDungById(id).enqueue(new Callback<TinTuyenDung>() {
            @Override
            public void onResponse(Call<TinTuyenDung> call, Response<TinTuyenDung> response) {
                TinTuyenDung tinTuyenDung = response.body();
                Intent intent = new Intent(getApplicationContext(), FormCapNhatTinTuyenDung.class);
                intent.putExtra("tintuyendung", (Serializable) tinTuyenDung);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<TinTuyenDung> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
                Log.d("ddgfffffffffffff", t.getMessage());

            }
        });



    }
}