package com.example.expendlistview.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expendlistview.API.DeTaiNghienCuuApi;
import com.example.expendlistview.Model.DeTaiNghienCuu;
import com.example.expendlistview.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKyDeTai extends AppCompatActivity {
    EditText hovatenInput, masvInput, khoaInput, tendetaiInput, linhvucInput, thoigianInput, kinhphiInput, ghichuInput;
    Button dangkyButton;
    DeTaiNghienCuu deTaiNghienCuu;
    String hovaten, masv, khoa, tendetai, linhvuc, thoigian, kinhphi, ghichu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_de_tai);
        hovatenInput = findViewById(R.id.hovaten_input);
        masvInput = findViewById(R.id.masinhvien_input);
        khoaInput = findViewById(R.id.khoa_input);
        tendetaiInput = findViewById(R.id.detai_input);
        linhvucInput = findViewById(R.id.linhvuc_input);
        thoigianInput = findViewById(R.id.thoigian_input);
        kinhphiInput = findViewById(R.id.kinhphi_input);
        ghichuInput = findViewById(R.id.ghichu_input);
        dangkyButton = findViewById(R.id.dangky_input);
        dangkyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hovaten = hovatenInput.getText().toString();
                masv = masvInput.getText().toString();
                khoa = khoaInput.getText().toString();
                tendetai = tendetaiInput.getText().toString();
                linhvuc = linhvucInput.getText().toString();
                thoigian = thoigianInput.getText().toString();
                kinhphi = kinhphiInput.getText().toString();
                ghichu= ghichuInput.getText().toString();

                deTaiNghienCuu = new DeTaiNghienCuu();
                deTaiNghienCuu.setHoVaTen(hovaten);
                deTaiNghienCuu.setMaSV(masv);
                deTaiNghienCuu.setKhoa(khoa);
                deTaiNghienCuu.setTenDeTai(tendetai);
                deTaiNghienCuu.setLinhVuc(linhvuc);
                deTaiNghienCuu.setThoiGianDuKien(thoigian);
                deTaiNghienCuu.setKinhPhi(kinhphi);
                deTaiNghienCuu.setGhiChu(ghichu);

                if(hovaten.trim().equals("")){
                    hovatenInput.setError("Không được bỏ trống trường thông tin này");

                }

                else if(masv.trim().equals("")){
                    masvInput.setError("Không được bỏ trống trường thông tin này");

                }
                else if(khoa.trim().equals("")){
                    khoaInput.setError("Không được bỏ trống trường thông tin này");

                }
                else if(tendetai.trim().equals("")){
                    tendetaiInput.setError("Không được bỏ trống trường thông tin này");

                }
                else if(linhvuc.trim().equals("")){
                    linhvucInput.setError("Không được bỏ trống trường thông tin này");

                }
                else if(kinhphi.trim().equals("")){
                    kinhphiInput.setError("Không được bỏ trống trường thông tin này");

                }
                else if(thoigian.trim().equals("")){
                    thoigianInput.setError("Không được bỏ trống trường thông tin này");

                }
                else if(ghichu.trim().equals("")){
                    ghichuInput.setError("Không được bỏ trống trường thông tin này");

                }


                else {
                    dangKyDeTai(deTaiNghienCuu);
                }



            }
        });
    }
    public void dangKyDeTai(DeTaiNghienCuu deTaiNghienCuu){
        DeTaiNghienCuuApi.deTaiNghienCuuApi.addDeTai(deTaiNghienCuu).enqueue(new Callback<DeTaiNghienCuu>() {
            @Override
            public void onResponse(Call<DeTaiNghienCuu> call, Response<DeTaiNghienCuu> response) {
                Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(Call<DeTaiNghienCuu> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
                Log.d("ddgfffffffffffff", t.getMessage());

            }
        });

    }
}