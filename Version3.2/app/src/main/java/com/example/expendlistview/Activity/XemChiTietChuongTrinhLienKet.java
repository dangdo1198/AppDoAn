package com.example.expendlistview.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expendlistview.API.ChuongTrinhLienKetApi;
import com.example.expendlistview.Model.CTLKDoanhNghiep;
import com.example.expendlistview.Activity.FormPhanHoiDeXuatChuongTrinhLienKet;
import com.example.expendlistview.R;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XemChiTietChuongTrinhLienKet extends AppCompatActivity {
    TextView idTxt, tenChuongTrinhTxt, tenDoanhNghiepTxt, noiDungChinhTxt, thoiGianDuKienTxt, ghiChuTxt, chucVuTxt, nguoiDaiDienTxt;
    CTLKDoanhNghiep ctlkDoanhNghiep;
    ScrollView scrollView;
    ImageView quayLaiImg, diTiepImg;
    int idChuongTrinh;
    Context context;
    Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_chi_tiet_chuong_trinh_lien_ket);
        idTxt = findViewById(R.id.id_txt);
        tenChuongTrinhTxt = findViewById(R.id.tenchuongtrinh_txt);
        tenDoanhNghiepTxt = findViewById(R.id.tendoanhnghiep_txt);
        noiDungChinhTxt = findViewById(R.id.noidungchinh_txt);
        thoiGianDuKienTxt = findViewById(R.id.thoigiandukien_txt);
        ghiChuTxt = findViewById(R.id.ghichu_txt);
        chucVuTxt = findViewById(R.id.chucvu_txt);
        nguoiDaiDienTxt = findViewById(R.id.nguoidaidien_txt);
        scrollView = findViewById(R.id.scrollView1);
        quayLaiImg = findViewById(R.id.quaylai_img);
        diTiepImg = findViewById(R.id.ditiep_img);

        Intent intent = getIntent();
        ctlkDoanhNghiep =(CTLKDoanhNghiep)intent.getExtras().getSerializable("xemchitietchuongtrinhlienket");
        tenChuongTrinhTxt.setText(ctlkDoanhNghiep.getTenChuongTrinh());
        tenDoanhNghiepTxt.setText(ctlkDoanhNghiep.getTenDoanhNghiep());
        noiDungChinhTxt.setText(ctlkDoanhNghiep.getNoiDungChinh());
        thoiGianDuKienTxt.setText(ctlkDoanhNghiep.getThoiGianDuKien());
        ghiChuTxt.setText(ctlkDoanhNghiep.getGhiChu());
        chucVuTxt.setText(ctlkDoanhNghiep.getChucVu());
        nguoiDaiDienTxt.setText(ctlkDoanhNghiep.getNguoiDaiDien());
        idTxt.setText(String.valueOf(ctlkDoanhNghiep.getId()));

        quayLaiImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), QuanLyChuongTrinhLienKet.class);
                startActivity(intent);


            }
        });
        diTiepImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idChuongTrinh = Integer.parseInt((String) idTxt.getText());
                getChuongTrinhLienKetById(idChuongTrinh);

            }
        });

    }
    public void getChuongTrinhLienKetById(int id){
        ChuongTrinhLienKetApi.chuongTrinhLienKetApi.getDeXuatById(id).enqueue(new Callback<CTLKDoanhNghiep>() {
            @Override
            public void onResponse(Call<CTLKDoanhNghiep> call, Response<CTLKDoanhNghiep> response) {
                CTLKDoanhNghiep ctlkDoanhNghiep = response.body();
                Intent intent = new Intent(getApplicationContext(), FormPhanHoiDeXuatChuongTrinhLienKet.class);
                intent.putExtra("chuongtrinhlienket", (Serializable) ctlkDoanhNghiep);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<CTLKDoanhNghiep> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
                Log.d("ddgfffffffffffff", t.getMessage());

            }
        });


    }
}