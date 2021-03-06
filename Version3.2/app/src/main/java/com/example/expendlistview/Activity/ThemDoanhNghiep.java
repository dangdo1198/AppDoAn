package com.example.expendlistview.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expendlistview.API.DoanhNghiepHopTacApi;
import com.example.expendlistview.Model.DoanhNghiepHopTac;
import com.example.expendlistview.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemDoanhNghiep extends AppCompatActivity {
    EditText tenDoanhNghiepEdt, giamDocEdt, linhVucEdt, namHopTacEdt, hotlineEdt, websiteEdt, truSoEdt;
    Button themBtn;
    String tendoanhnghiep, giamdoc, linhvuc, namhoptac, hotline, website, truso;
    DoanhNghiepHopTac doanhNghiepHopTac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_doanh_nghiep);
        tenDoanhNghiepEdt = findViewById(R.id.tendoanhnghiep_edt);
        giamDocEdt = findViewById(R.id.giamdoc_edt);
        linhVucEdt = findViewById(R.id.linhvuc_edt);
        namHopTacEdt = findViewById(R.id.namhoptac_edt);
        hotlineEdt = findViewById(R.id.hotline_edt);
        websiteEdt = findViewById(R.id.website_edt);
        truSoEdt = findViewById(R.id.truso_edt);
        themBtn = findViewById(R.id.themdoanhnghiep_btn);

        themBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tendoanhnghiep = tenDoanhNghiepEdt.getText().toString();
                giamdoc = giamDocEdt.getText().toString();
                linhvuc = linhVucEdt.getText().toString();
                namhoptac = namHopTacEdt.getText().toString();
                hotline = hotlineEdt.getText().toString();
                website = websiteEdt.getText().toString();
                truso = truSoEdt.getText().toString();

                doanhNghiepHopTac = new DoanhNghiepHopTac();
                doanhNghiepHopTac.setTenDoanhNghiep(tendoanhnghiep);
                doanhNghiepHopTac.setTenGiamDoc(giamdoc);
                doanhNghiepHopTac.setLinhVuc(linhvuc);
                doanhNghiepHopTac.setNamHopTac(namhoptac);
                doanhNghiepHopTac.setSoDienThoai(hotline);
                doanhNghiepHopTac.setWebsite(website);
                doanhNghiepHopTac.setTruSoChinh(truso);

                if(tendoanhnghiep.trim().equals("")){
                    tenDoanhNghiepEdt.setError("Thi???u t??n doanh nghi???p");

                }

                else if(giamdoc.trim().equals("")){
                    giamDocEdt.setError("Thi???u t??n gi??m ?????c");

                }
                  else if(namhoptac.trim().equals("")){
                    namHopTacEdt.setError("Ch??a c?? th??ng tin n??m h???p t??c");

                }
                    else if(linhvuc.trim().equals("")){
                    linhVucEdt.setError("Thi???u th??ng tin l??nh v???c");

                }
                      else if(truso.trim().equals("")){
                    truSoEdt.setError("C???p nh???t th??m ?????a ??i???m c???a doanh nghi???p");

                }
                        else if(hotline.trim().equals("")){
                    hotlineEdt.setError("Thi???u s??? ??i???n tho???i doanh nghi???p");

                }
                else if(website.trim().equals("")){
                    websiteEdt.setError("Ch??a ??i???n th??ng tin website");

                }

                else {
                    themDoanhNghiep(doanhNghiepHopTac);
                }
            }
        });


    }
    public void themDoanhNghiep(DoanhNghiepHopTac doanhNghiepHopTac){
        DoanhNghiepHopTacApi.doanhNghiepHopTacApi.addDoanhNghiep(doanhNghiepHopTac).enqueue(new Callback<DoanhNghiepHopTac>() {
            @Override
            public void onResponse(Call<DoanhNghiepHopTac> call, Response<DoanhNghiepHopTac> response) {
                Toast.makeText(getApplicationContext(), "???? th??m th??nh c??ng m???t doanh nghi???p v??o danh s??ch", Toast.LENGTH_LONG).show();
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

