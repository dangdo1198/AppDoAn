package com.example.expendlistview.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expendlistview.API.TaiLieuNghienCuuApi;
import com.example.expendlistview.Model.TaiLieuNghienCuu;
import com.example.expendlistview.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemTaiLieuNghienCuu extends AppCompatActivity {
    EditText tentailieu_input, tacgia_input, linhvuc_input, thoigian_input, linktai_input;
    Button add_button;
    String tenTaiLieu, tacGia, linhVuc, thoiGian, linkTai;
    TaiLieuNghienCuu taiLieuNghienCuu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_tai_lieu_nghien_cuu);
        tentailieu_input = findViewById(R.id.tentailieu_input);
        tacgia_input = findViewById(R.id.tacgia_input);
        linhvuc_input = findViewById(R.id.linhvuc_input);
        thoigian_input = findViewById(R.id.thoigian_input);
        linktai_input = findViewById(R.id.linktai_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenTaiLieu = tentailieu_input.getText().toString();
                tacGia = tacgia_input.getText().toString();
                linhVuc = linhvuc_input.getText().toString();
                thoiGian = thoigian_input.getText().toString();
                linkTai = linktai_input.getText().toString();

                taiLieuNghienCuu = new TaiLieuNghienCuu();
                taiLieuNghienCuu.setTenTaiLieu(tenTaiLieu);
                taiLieuNghienCuu.setTacGia(tacGia);
                taiLieuNghienCuu.setLinhVuc(linhVuc);
                taiLieuNghienCuu.setThoiGian(thoiGian);
                taiLieuNghienCuu.setLinkTai(linkTai);

                if(tenTaiLieu.trim().equals("")){
                    tentailieu_input.setError("Chưa điền thông tin tài liệu");

                }

                else if(tacGia.trim().equals("")){
                    tacgia_input.setError("Tác giả bị bỏ trống");

                }
                else if(linhVuc.trim().equals("")){
                    linktai_input.setError("Chưa có nội dung lĩnh vực");

                }
                else if(thoiGian.trim().equals("")){
                    thoigian_input.setError("Thiếu thông tin về thời gian");

                }


                else {
                    createData(taiLieuNghienCuu);
                }
            }
        });

    }
    public void createData(TaiLieuNghienCuu taiLieuNghienCuu) {

        TaiLieuNghienCuuApi.taiLieuNghienCuuApi.addTaiLieu(taiLieuNghienCuu).enqueue(new Callback<TaiLieuNghienCuu>() {
            @Override
            public void onResponse(Call<TaiLieuNghienCuu> call, Response<TaiLieuNghienCuu> response) {
                Toast.makeText(getApplicationContext(), "Đã thêm mới một tài liệu", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(Call<TaiLieuNghienCuu> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
                Log.d("ddgfffffffffffff", t.getMessage());
            }
        });
    }
}



