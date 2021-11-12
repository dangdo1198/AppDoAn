package com.example.expendlistview.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expendlistview.API.TinTuyenDungApi;
import com.example.expendlistview.Adapter.TinTuyenDungAdapter;
import com.example.expendlistview.Model.TinTuyenDung;
import com.example.expendlistview.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangTinTuyenDung extends AppCompatActivity {
    RecyclerView recyclerView;
    List<TinTuyenDung> lisTinTuyenDung;
    TinTuyenDungAdapter tinTuyenDungAdapter;
    EditText tenDoanhNghiepEdt, nguoiDangEdt, chucVuEdt, soDienThoaiEdt, emailEdt, websiteEdt, tieuDeEdt, noiDungEdt;
    Button dangBaiBtn;
    String tendoanhnghiep, nguoidang, chucvu, sodienthoai, email, website, tieude, noidung;
    TinTuyenDung tinTuyenDung;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_tin_tuyen_dung);
        tenDoanhNghiepEdt = findViewById(R.id.tendoanhnghiep_edt);
        nguoiDangEdt = findViewById(R.id.nguoidang_edt);
        chucVuEdt = findViewById(R.id.chucvu_edt);
        soDienThoaiEdt = findViewById(R.id.sodienthoai_edt);
        emailEdt = findViewById(R.id.email_edt);
        websiteEdt = findViewById(R.id.website_edt);
        tieuDeEdt = findViewById(R.id.tieude_edt);
        noiDungEdt = findViewById(R.id.noidung_edt);
        dangBaiBtn = findViewById(R.id.dangbai_btn);

        recyclerView = findViewById(R.id.recyclerView);
        clickCallApi();
        dangBaiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tendoanhnghiep = tenDoanhNghiepEdt.getText().toString();
                nguoidang = nguoiDangEdt.getText().toString();
                chucvu = chucVuEdt.getText().toString();
                sodienthoai = soDienThoaiEdt.getText().toString();
                email = emailEdt.getText().toString();
                website = websiteEdt.getText().toString();
                tieude = tieuDeEdt.getText().toString();
                noidung = noiDungEdt.getText().toString();

                tinTuyenDung = new TinTuyenDung();
                tinTuyenDung.setTenDoanhNghiep(tendoanhnghiep);
                tinTuyenDung.setNguoiDang(nguoidang);
                tinTuyenDung.setChucVu(chucvu);
                tinTuyenDung.setSoDienThoai(sodienthoai);
                tinTuyenDung.setEmail(email);
                tinTuyenDung.setWebsite(website);
                tinTuyenDung.setTieuDe(tieude);
                tinTuyenDung.setNoiDung(noidung);

                 if(tendoanhnghiep.trim().equals("")){
                    tenDoanhNghiepEdt.setError("Chưa điền thông tin vào ô này!");
                }
               else if(nguoidang.trim().equals("")){
                    nguoiDangEdt.setError("Chưa điền thông tin vào ô này!");
                }
                else if(chucvu.trim().equals("")){
                    chucVuEdt.setError("Chưa điền thông tin vào ô này!");
                }
                else if(sodienthoai.trim().equals("")){
                    soDienThoaiEdt.setError("Chưa điền thông tin vào ô này!");
                }
                else if(email.trim().equals("")){
                    emailEdt.setError("Chưa điền thông tin vào ô này!");
                }
                else if(website.trim().equals("")){
                    websiteEdt.setError("Chưa điền thông tin vào ô này!");
                }
                else if(tieude.trim().equals("")){
                    tieuDeEdt.setError("Chưa điền thông tin vào ô này!");
                }
                else if(noidung.trim().equals("")){
                    noiDungEdt.setError("Chưa điền thông tin vào ô này!");
                }
                else {
                    dangTinTuyenDung(tinTuyenDung);
                }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clickCallApi();

                    }
                },1000);
                Toast.makeText(getApplicationContext(), "Tạo thành công một tin tuyển dụng mới", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void clickCallApi(){
        TinTuyenDungApi.tinTuyenDungApi.allTinTuyenDung().enqueue(new Callback<List<TinTuyenDung>>() {
            @Override
            public void onResponse(Call<List<TinTuyenDung>> call, Response<List<TinTuyenDung>> response) {
                lisTinTuyenDung = response.body();
                tinTuyenDungAdapter = new TinTuyenDungAdapter(DangTinTuyenDung.this, DangTinTuyenDung.this,lisTinTuyenDung);
                recyclerView.setAdapter(tinTuyenDungAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(DangTinTuyenDung.this));

            }

            @Override
            public void onFailure(Call<List<TinTuyenDung>> call, Throwable t) {

            }
        });
    }
    public void dangTinTuyenDung(TinTuyenDung tinTuyenDung){
        TinTuyenDungApi.tinTuyenDungApi.addTinTuyenDung(tinTuyenDung).enqueue(new Callback<TinTuyenDung>() {
            @Override
            public void onResponse(Call<TinTuyenDung> call, Response<TinTuyenDung> response) {
//                Toast.makeText(getBaseContext(), "Đã đăng một tin tuyển dụng mới lên hệ thống", Toast.LENGTH_LONG).show();
//                finish();

//                Toast.makeText(activity, "Đã đăng một tin tuyển dụng mới lên hệ thống", Toast.LENGTH_LONG).show();
//                finish();
            }

            @Override
            public void onFailure(Call<TinTuyenDung> call, Throwable t) {

            }
        });
    }
}