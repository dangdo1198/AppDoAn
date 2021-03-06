package com.example.expendlistview.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expendlistview.API.ChuongTrinhLienKetApi;
import com.example.expendlistview.Model.CTLKDoanhNghiep;
import com.example.expendlistview.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormDeXuatChuongTrinhLienKet extends AppCompatActivity {
    EditText tenChuongTrinhEdt, tenDoanhNghiepEdt, nguoiDaiDienEdt, chucVuEdt, noiDungChinhEdt, thoiGianDuKienEdt,ghiChuEdt;
    Button guiDeXuatBtn, danhSachDeXuatDaGuiBtn;
    String tenchuongtrinh, tendoanhnghiep, nguoidaidien, chucvu, noidungchinh, thoigiandukien, ghichu;
    CTLKDoanhNghiep ctlkDoanhNghiep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_de_xuat_chuong_trinh_lien_ket);
        tenChuongTrinhEdt = findViewById(R.id.tenchuongtrinh_edt);
        tenDoanhNghiepEdt = findViewById(R.id.tendoanhnghiep_edt);
        nguoiDaiDienEdt = findViewById(R.id.nguoidaidien_edt);
        chucVuEdt = findViewById(R.id.chucvu_edt);
        noiDungChinhEdt = findViewById(R.id.noidungchinh_edt);
        thoiGianDuKienEdt = findViewById(R.id.thoigiandukien_edt);
        ghiChuEdt = findViewById(R.id.ghichu_edt);
        guiDeXuatBtn =findViewById(R.id.guidexuat_btn);
        danhSachDeXuatDaGuiBtn = findViewById(R.id.danhsachdexuat_btn);


        guiDeXuatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenchuongtrinh = tenChuongTrinhEdt.getText().toString();
                tendoanhnghiep = tenDoanhNghiepEdt.getText().toString();
                nguoidaidien = nguoiDaiDienEdt.getText().toString();
                chucvu = chucVuEdt.getText().toString();
                noidungchinh = noiDungChinhEdt.getText().toString();
                thoigiandukien = thoiGianDuKienEdt.getText().toString();
                ghichu = ghiChuEdt.getText().toString();

                ctlkDoanhNghiep = new CTLKDoanhNghiep();
                ctlkDoanhNghiep.setTenChuongTrinh(tenchuongtrinh);
                ctlkDoanhNghiep.setTenDoanhNghiep(tendoanhnghiep);
                ctlkDoanhNghiep.setNguoiDaiDien(nguoidaidien);
                ctlkDoanhNghiep.setChucVu(chucvu);
                ctlkDoanhNghiep.setNoiDungChinh(noidungchinh);
                ctlkDoanhNghiep.setThoiGianDuKien(thoigiandukien);
                ctlkDoanhNghiep.setGhiChu(ghichu);

                if(tenchuongtrinh.trim().equals("")){
                    tenChuongTrinhEdt.setError("??i???n th??ng tin ch????ng tr??nh li??n k???t");
                }
                if(tendoanhnghiep.trim().equals("")){
                    tenDoanhNghiepEdt.setError("??i???n th??ng tin t??n doanh nghi???p c???a b???n ");
                }
                if(nguoidaidien.trim().equals("")){
                    nguoiDaiDienEdt.setError("??i???n ?????y ????? th??ng tin h??? v?? t??n ng?????i ?????i di???n");
                }
                if(chucvu.trim().equals("")){
                    chucVuEdt.setError("Ch??a c?? th??ng tin ch???c v???");
                }
                if(noidungchinh.trim().equals("")){
                    noiDungChinhEdt.setError("B???t bu???c ghi r?? n???i dung ch??nh ");
                }
                if(thoigiandukien.trim().equals("")){
                    thoiGianDuKienEdt.setError("Thi???u th??ng tin th???i gian d??? ki???n");
                }
                if(ghichu.trim().equals("")){
                    ghiChuEdt.setError("B???t bu???c ph???i ??i???n th??ng tin n??y ");
                }
                else {
                    guiDeXuatChuongTrinhLienKet(ctlkDoanhNghiep);
                }




            }
        });
        danhSachDeXuatDaGuiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), DeXuatChuaCoPhanHoi.class);
                startActivity(intent);

            }
        });
    }
    public void guiDeXuatChuongTrinhLienKet(CTLKDoanhNghiep ctlkDoanhNghiep){
        ChuongTrinhLienKetApi.chuongTrinhLienKetApi.guiDeXuat(ctlkDoanhNghiep).enqueue(new Callback<CTLKDoanhNghiep>() {
            @Override
            public void onResponse(Call<CTLKDoanhNghiep> call, Response<CTLKDoanhNghiep> response) {
                Toast.makeText(getApplicationContext(), "???? t???o v?? g???i th??nh c??ng m???t ????? xu???t ch????ng tr??nh li??n k???t c???a doanh nghi???p b???n", Toast.LENGTH_LONG).show();
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