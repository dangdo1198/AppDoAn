package com.example.expendlistview.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expendlistview.API.DoanhNghiepHopTacApi;
import com.example.expendlistview.Model.DeTaiNghienCuu;
import com.example.expendlistview.Model.DoanhNghiepHopTac;
import com.example.expendlistview.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormCapNhatDoanhNghiep extends AppCompatActivity {
    EditText idEdt, tenDoanhNghiepEdt, giamDocEdt, linhVucEdt, namHopTacEdt, hotlineEdt,websiteEdt, truSoEdt;
    Button capNhatBtn;
    String  tendoanhnghiep, giamdoc, linhvuc, namhoptac, hotline, website, truso;
    DoanhNghiepHopTac doanhNghiepHopTac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cap_nhat_doanh_nghiep);


        idEdt = findViewById(R.id.id_edt);
        giamDocEdt = findViewById(R.id.giamdoc_edt);
        linhVucEdt = findViewById(R.id.linhvuc_edt);
        tenDoanhNghiepEdt = findViewById(R.id.tendoanhnghiep_edt);
        namHopTacEdt = findViewById(R.id.namhoptac_edt);
        hotlineEdt = findViewById(R.id.hotline_edt);
        websiteEdt = findViewById(R.id.website_edt);
        truSoEdt = findViewById(R.id.truso_edt);
        capNhatBtn = findViewById(R.id.capnhatdoanhnghiep_btn);

        Intent intent = getIntent();
        doanhNghiepHopTac = (DoanhNghiepHopTac)intent.getExtras().getSerializable("doanhnghiep");
        idEdt.setText(String.valueOf(doanhNghiepHopTac.getId()));
        tenDoanhNghiepEdt.setText(doanhNghiepHopTac.getTenDoanhNghiep());
        giamDocEdt.setText(doanhNghiepHopTac.getTenGiamDoc());
        linhVucEdt.setText(doanhNghiepHopTac.getLinhVuc());
        namHopTacEdt.setText(doanhNghiepHopTac.getNamHopTac());
        hotlineEdt.setText(doanhNghiepHopTac.getSoDienThoai());
        websiteEdt.setText(doanhNghiepHopTac.getWebsite());
        truSoEdt.setText(doanhNghiepHopTac.getTruSoChinh());

        capNhatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tendoanhnghiep = tenDoanhNghiepEdt.getText().toString();
                giamdoc = giamDocEdt.getText().toString();
                linhvuc = linhVucEdt.getText().toString();
                namhoptac = namHopTacEdt.getText().toString();
                truso = truSoEdt.getText().toString();
                hotline = hotlineEdt.getText().toString();
                website = websiteEdt.getText().toString();

                doanhNghiepHopTac.setTenDoanhNghiep(tendoanhnghiep);
                doanhNghiepHopTac.setTenGiamDoc(giamdoc);
                doanhNghiepHopTac.setLinhVuc(linhvuc);
                doanhNghiepHopTac.setNamHopTac(namhoptac);
                doanhNghiepHopTac.setTruSoChinh(truso);
                doanhNghiepHopTac.setSoDienThoai(hotline);
                doanhNghiepHopTac.setWebsite(website);

                updateDoanhNghiep(doanhNghiepHopTac);


                Intent intent = new Intent(getBaseContext(), DanhSachDoanhNghiep.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Cập nhật thành công doanh nghiệp: "+doanhNghiepHopTac.getTenDoanhNghiep(), Toast.LENGTH_LONG).show();
            }
        });

    }
    public void updateDoanhNghiep(DoanhNghiepHopTac doanhNghiepHopTac){
        DoanhNghiepHopTacApi.doanhNghiepHopTacApi.updateDoanhNghiep(doanhNghiepHopTac).enqueue(new Callback<DoanhNghiepHopTac>() {
            @Override
            public void onResponse(Call<DoanhNghiepHopTac> call, Response<DoanhNghiepHopTac> response) {
                finish();
            }

            @Override
            public void onFailure(Call<DoanhNghiepHopTac> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
                Log.d("ddgfffffffffffff", t.getMessage());
            }
        });
    }
}