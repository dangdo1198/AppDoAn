package com.example.expendlistview.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class FormPheDuyetDeTai extends AppCompatActivity {
    EditText id_edt, tensv_edt, tendetai_edt, quyetdinh_edt, lydo_edt;
    Button luu_btn;
    DeTaiNghienCuu deTaiNghienCuu;
    String quyetdinh, lydo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_phe_duyet_de_tai);
        id_edt = findViewById(R.id.id_txt);
        tendetai_edt = findViewById(R.id.tendetai_txt);
        tensv_edt = findViewById(R.id.tensv_txt);
        quyetdinh_edt = findViewById(R.id.quyetdinh_txt);
        lydo_edt = findViewById(R.id.lydo_txt);
        luu_btn = findViewById(R.id.luu_button);

        // Hiển thị đề tài được chọn theo id từ Pheduyetdetaiadapter lấy ra đối tượng đề tài và hiển thị đối tượng ra input.
        Intent intent = getIntent();
        deTaiNghienCuu= (DeTaiNghienCuu) intent.getExtras().getSerializable("detai");
        id_edt.setText(String.valueOf(deTaiNghienCuu.getId()));
        tensv_edt.setText(deTaiNghienCuu.getHoVaTen());
        tendetai_edt.setText(deTaiNghienCuu.getTenDeTai());
        quyetdinh_edt.setText(deTaiNghienCuu.getTrangThaiDuyet());
        lydo_edt.setText(deTaiNghienCuu.getLyDo());


        // set sự kiện nhấn nút button save thì dữ liệu giảng viên phê duyệt sẽ được lưu và cập nhật lại
        luu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chỉ update quyết định phê duyệt và lý do.
                quyetdinh = quyetdinh_edt.getText().toString();
                lydo = lydo_edt.getText().toString();

                deTaiNghienCuu.setTrangThaiDuyet(quyetdinh);
                deTaiNghienCuu.setLyDo(lydo);

                updateDeTai(deTaiNghienCuu);

                Intent intent = new Intent(getBaseContext(), PheDuyetDeTai.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Hoàn thành phê duyệt đề tài: "+deTaiNghienCuu.getTenDeTai(), Toast.LENGTH_LONG).show();
            }
        });




    }
    public  void updateDeTai(DeTaiNghienCuu deTaiNghienCuu){
        DeTaiNghienCuuApi.deTaiNghienCuuApi.pheDuyetDeTai(deTaiNghienCuu).enqueue(new Callback<DeTaiNghienCuu>() {
            @Override
            public void onResponse(Call<DeTaiNghienCuu> call, Response<DeTaiNghienCuu> response) {
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