package com.example.expendlistview.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expendlistview.API.ChuongTrinhLienKetApi;
import com.example.expendlistview.Model.CTLKDoanhNghiep;

import com.example.expendlistview.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormPhanHoiDeXuatChuongTrinhLienKet extends AppCompatActivity {
    TextView idTxt, tenChuongTrinhTxt, tenDoanhNghiepTxt;
    EditText quyetDinhEdt, loiNhanEdt;
    ImageView saveImg;
    String  quyetdinh, loinhan;
    CTLKDoanhNghiep ctlkDoanhNghiep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_phan_hoi_de_xuat_chuong_trinh_lien_ket);

        idTxt = findViewById(R.id.id_txt);
        tenChuongTrinhTxt = findViewById(R.id.tenchuongtrinh_txt);
        tenDoanhNghiepTxt = findViewById(R.id.tendoanhnghiep_txt);
        quyetDinhEdt = findViewById(R.id.quyetdinh_edt);
        loiNhanEdt = findViewById(R.id.loinhan_edt);
        saveImg = findViewById(R.id.save_img);

        Intent intent = getIntent();
        ctlkDoanhNghiep = (CTLKDoanhNghiep) intent.getExtras().getSerializable("chuongtrinhlienket");
        idTxt.setText(String.valueOf(ctlkDoanhNghiep.getId()));
        tenDoanhNghiepTxt.setText(ctlkDoanhNghiep.getTenDoanhNghiep());
        tenChuongTrinhTxt.setText(ctlkDoanhNghiep.getTenChuongTrinh());
        quyetDinhEdt.setText(ctlkDoanhNghiep.getTrangThaiXacNhan());
        loiNhanEdt.setText(ctlkDoanhNghiep.getLyDo());

        saveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quyetdinh = quyetDinhEdt.getText().toString();
                loinhan = loiNhanEdt.getText().toString();

                ctlkDoanhNghiep.setTrangThaiXacNhan(quyetdinh);
                ctlkDoanhNghiep.setLyDo(loinhan);

                phanHoiDeXuat(ctlkDoanhNghiep);

                Intent intent = new Intent(getBaseContext(), QuanLyChuongTrinhLienKet.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Hoàn thành phản hồi tới đề xuất: "+ctlkDoanhNghiep.getTenChuongTrinh(), Toast.LENGTH_LONG).show();

            }
        });



    }

    public void phanHoiDeXuat(CTLKDoanhNghiep ctlkDoanhNghiep){
        ChuongTrinhLienKetApi.chuongTrinhLienKetApi.phanHoiDeXuat(ctlkDoanhNghiep).enqueue(new Callback<CTLKDoanhNghiep>() {
            @Override
            public void onResponse(Call<CTLKDoanhNghiep> call, Response<CTLKDoanhNghiep> response) {
                finish();
            }

            @Override
            public void onFailure(Call<CTLKDoanhNghiep> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
                Log.d("ddgfffffffffffff", t.getMessage());

            }
        });
    }
}