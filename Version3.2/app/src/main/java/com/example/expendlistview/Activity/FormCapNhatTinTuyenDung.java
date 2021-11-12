package com.example.expendlistview.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expendlistview.API.TinTuyenDungApi;
import com.example.expendlistview.Model.DoanhNghiepHopTac;
import com.example.expendlistview.Model.TinTuyenDung;
import com.example.expendlistview.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormCapNhatTinTuyenDung extends AppCompatActivity {
    TextView idTxt;
    EditText tenDoanhNghiepEdt, tieuDeEdt, noiDungEdt, soDienThoaiEdt, emailEdt, websiteEdt;
    Button saveBtn;
    String tendoanhnghiep, tieude, noidung, sodienthoai, email, website;
    TinTuyenDung tinTuyenDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cap_nhat_tin_tuyen_dung);
        idTxt = findViewById(R.id.id_txt);
        tenDoanhNghiepEdt = findViewById(R.id.tendoanhnghiep_edt);
        tieuDeEdt = findViewById(R.id.tieude_edt);
        noiDungEdt = findViewById(R.id.noidungchinh_edt);
        soDienThoaiEdt = findViewById(R.id.sodienthoai_edt);
        emailEdt = findViewById(R.id.email_edt);
        websiteEdt = findViewById(R.id.website_edt);
        saveBtn = findViewById(R.id.save_btn);


        Intent intent = getIntent();
        tinTuyenDung = (TinTuyenDung)intent.getExtras().getSerializable("tintuyendung");
        idTxt.setText(String.valueOf(tinTuyenDung.getId()));
        tenDoanhNghiepEdt.setText(tinTuyenDung.getTenDoanhNghiep());
        tieuDeEdt.setText(tinTuyenDung.getTieuDe());
        noiDungEdt.setText(tinTuyenDung.getNoiDung());
        soDienThoaiEdt.setText(tinTuyenDung.getSoDienThoai());
        emailEdt.setText(tinTuyenDung.getEmail());
        websiteEdt.setText(tinTuyenDung.getWebsite());

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tendoanhnghiep = tenDoanhNghiepEdt.getText().toString();
                tieude = tieuDeEdt.getText().toString();
                noidung = noiDungEdt.getText().toString();
                sodienthoai = soDienThoaiEdt.getText().toString();
                email = emailEdt.getText().toString();
                website = websiteEdt.getText().toString();

                tinTuyenDung.setTenDoanhNghiep(tendoanhnghiep);
                tinTuyenDung.setTieuDe(tieude);
                tinTuyenDung.setNoiDung(noidung);
                tinTuyenDung.setSoDienThoai(sodienthoai);
                tinTuyenDung.setEmail(email);
                tinTuyenDung.setWebsite(website);

                updateTin(tinTuyenDung);

                Intent intent = new Intent(getBaseContext(), DangTinTuyenDung.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Hoàn thành cập nhật tin", Toast.LENGTH_LONG).show();


            }
        });

    }
    public void updateTin(TinTuyenDung tinTuyenDung){
        TinTuyenDungApi.tinTuyenDungApi.updateTinTuyenDung(tinTuyenDung).enqueue(new Callback<TinTuyenDung>() {
            @Override
            public void onResponse(Call<TinTuyenDung> call, Response<TinTuyenDung> response) {
                finish();
            }

            @Override
            public void onFailure(Call<TinTuyenDung> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
                Log.d("ddgfffffffffffff", t.getMessage());

            }
        });
    }
}