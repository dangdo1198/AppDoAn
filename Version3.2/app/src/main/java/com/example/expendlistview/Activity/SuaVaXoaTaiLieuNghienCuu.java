package com.example.expendlistview.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expendlistview.API.TaiLieuNghienCuuApi;
import com.example.expendlistview.Model.TaiLieuNghienCuu;
import com.example.expendlistview.R;


import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuaVaXoaTaiLieuNghienCuu extends AppCompatActivity {
    EditText tentailieu_input, tacgia_input, linhvuc_input, thoigian_input, linktai_input;
    Button edit_button, delete_input;
    String tentailieu, tacgia, linhvuc, thoigian, linktai;
//    String tenTaiLieu, tacGia, linhVuc, thoiGian, linkTai;
    TaiLieuNghienCuu taiLieu;
    Context context;
    Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_va_xoa_tai_lieu_nghien_cuu);

        tentailieu_input = findViewById(R.id.tentailieu_input1);
        tacgia_input = findViewById(R.id.tacgia_input1);
        linhvuc_input = findViewById(R.id.linhvuc_input1);
        thoigian_input = findViewById(R.id.thoigian_input1);
        linktai_input = findViewById(R.id.linktai_input1);
        edit_button = findViewById(R.id.edit_button1);
        delete_input = findViewById(R.id.delete_button1);

        Intent intent = getIntent();
        taiLieu= (TaiLieuNghienCuu) intent.getExtras().getSerializable("tailing");

        tentailieu_input.setText(taiLieu.getTenTaiLieu());
        tacgia_input.setText(taiLieu.getTacGia());
        linhvuc_input.setText(taiLieu.getLinhVuc());
        thoigian_input.setText(taiLieu.getThoiGian());
        linktai_input.setText(taiLieu.getLinkTai());

//        getAndSetIntentData();

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tentailieu = tentailieu_input.getText().toString();
                tacgia = tacgia_input.getText().toString();
                linhvuc = linhvuc_input.getText().toString();
                thoigian = thoigian_input.getText().toString();
                linktai = linktai_input.getText().toString();

                taiLieu.setTenTaiLieu(tentailieu);
                taiLieu.setTacGia(tacgia);
                taiLieu.setLinhVuc(linhvuc);
                taiLieu.setThoiGian(thoigian);
                taiLieu.setLinkTai(linktai);

                upDateTaiLieuApi(taiLieu);
                Intent intent = new Intent(getBaseContext(), QuanLyTaiLieu.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_LONG).show();

            }
        });

        delete_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmDialog();
            }
        });



    }

//    void getAndSetIntentData(){
//        if(getIntent().hasExtra("tentailieu")&& getIntent().hasExtra("tacgia")&&
//                getIntent().hasExtra("linhvuc")&& getIntent().hasExtra("thoigian")
//                && getIntent().hasExtra("linktai")){
//
//            tentailieu = getIntent().getStringExtra("tentailieu");
//            tacgia = getIntent().getStringExtra("tacgia");
//            linhvuc = getIntent().getStringExtra("linhvuc");
//            thoigian = getIntent().getStringExtra("thoigian");
//            linktai = getIntent().getStringExtra("linktai");
//           tentailieu_input.setText(tentailieu);
//           tacgia_input.setText(tacgia);
//           linhvuc_input.setText(linhvuc);
//           thoigian_input.setText(thoigian);
//           linktai_input.setText(linktai);
//
//
//        }else{
//            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
//        }
//    }

   public void upDateTaiLieuApi(TaiLieuNghienCuu taiLieu){



       TaiLieuNghienCuuApi.taiLieuNghienCuuApi.updateTaiLieu(taiLieu).enqueue(new Callback<TaiLieuNghienCuu>() {
           @Override
           public void onResponse(Call<TaiLieuNghienCuu> call, Response<TaiLieuNghienCuu> response) {

               finish();
           }

           @Override
           public void onFailure(Call<TaiLieuNghienCuu> call, Throwable t) {
               Toast.makeText(getApplicationContext(), "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
               Log.d("ddgfffffffffffff", t.getMessage());

           }
       });



    }

   public void confirmDialog(){
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("XÓA TÀI LIỆU");
        builder.setMessage("Bạn có muốn xóa:  "+ taiLieu.getTenTaiLieu()+" ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
//                myDB.deleteOneRow(id);
                deleteTaiLieu(taiLieu.getId());
                Intent intent = new Intent(getBaseContext(), QuanLyTaiLieu.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Xóa tài liệu thành công", Toast.LENGTH_LONG).show();



            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    public void deleteTaiLieu(int id){

        TaiLieuNghienCuuApi.taiLieuNghienCuuApi.deleteTaiLieu(id).enqueue(new Callback<TaiLieuNghienCuu>() {
            @Override
            public void onResponse(Call<TaiLieuNghienCuu> call, Response<TaiLieuNghienCuu> response) {


            }

            @Override
            public void onFailure(Call<TaiLieuNghienCuu> call, Throwable t) {

            }
        });

    }
}