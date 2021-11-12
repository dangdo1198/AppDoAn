package com.example.expendlistview.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expendlistview.API.ChuongTrinhLienKetApi;

import com.example.expendlistview.Activity.FormPhanHoiDeXuatChuongTrinhLienKet;
import com.example.expendlistview.Activity.QuanLyChuongTrinhLienKet;
import com.example.expendlistview.Activity.XemChiTietChuongTrinhLienKet;
import com.example.expendlistview.Model.CTLKDoanhNghiep;

import com.example.expendlistview.R;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuanLyChuongTrinhLienKetAdapter extends RecyclerView.Adapter<QuanLyChuongTrinhLienKetAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private List<CTLKDoanhNghiep> listCtklDoanhNghiep;
    int idChuongTrinh;
    CTLKDoanhNghiep ctlkDoanhNghiep;


    public QuanLyChuongTrinhLienKetAdapter(Context context, Activity activity, List<CTLKDoanhNghiep> listCtklDoanhNghiep ) {
        this.context = context;
        this.activity = activity;
        this.listCtklDoanhNghiep = listCtklDoanhNghiep;
    }

    @NonNull
    @Override
    public QuanLyChuongTrinhLienKetAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_quan_ly_de_xuat_chuong_trinh_lien_ket,parent,false);
        return new QuanLyChuongTrinhLienKetAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuanLyChuongTrinhLienKetAdapter.MyViewHolder holder, int position) {
        CTLKDoanhNghiep ctlkDoanhNghiep = listCtklDoanhNghiep.get(position);

        holder.idTxt.setText(String.valueOf(ctlkDoanhNghiep.getId()));
       holder.tenChuongTrinhTxt.setText(String.valueOf(ctlkDoanhNghiep.getTenChuongTrinh()));
       holder.tenDoanhNghiepTxt.setText(String.valueOf(ctlkDoanhNghiep.getTenDoanhNghiep()));
       holder.nguoiDaiDienTxt.setText(String.valueOf(ctlkDoanhNghiep.getNguoiDaiDien()));
       holder.chucVuTxt.setText(String.valueOf(ctlkDoanhNghiep.getChucVu()));
       holder.trangThaiTxt.setText(String.valueOf(ctlkDoanhNghiep.getTrangThaiXacNhan()));

        holder.phanHoiImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Nhấn edit", Toast.LENGTH_LONG).show();
                // code phe duyet de tai
                idChuongTrinh = Integer.parseInt((String)holder.idTxt.getText());
                getChuongTrinhLienKetById(idChuongTrinh);
            }
        });
        holder.xoaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               idChuongTrinh= Integer.parseInt((String)holder.idTxt.getText());
                confirmDialog();
            }
        });
        holder.xemChiTietImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                idChuongTrinh = Integer.parseInt((String)holder.idTxt.getText());
                getChuongTrinhLienKetByIdXemChiTiet(idChuongTrinh);
                //
            }
        });



    }

    @Override
    public int getItemCount() {
        return listCtklDoanhNghiep.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView idTxt,tenChuongTrinhTxt, tenDoanhNghiepTxt, nguoiDaiDienTxt, chucVuTxt, trangThaiTxt;
        ImageView xemChiTietImg, phanHoiImg, xoaImg;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            idTxt = itemView.findViewById(R.id.id_txt);
            tenChuongTrinhTxt = itemView.findViewById(R.id.tenchuongtrinh_txt);
            tenDoanhNghiepTxt = itemView.findViewById(R.id.tendoanhnghiep_txt);
            nguoiDaiDienTxt = itemView.findViewById(R.id.nguoidaidien_txt);
            chucVuTxt = itemView.findViewById(R.id.chucvu_txt);
            trangThaiTxt = itemView.findViewById(R.id.trangthai_txt);
            xemChiTietImg = itemView.findViewById(R.id.chitiet_img);
            phanHoiImg = itemView.findViewById(R.id.phanhoi_img);
            xoaImg = itemView.findViewById(R.id.xoa_img);






        }
    }


    public void getChuongTrinhLienKetById(int id){
        ChuongTrinhLienKetApi.chuongTrinhLienKetApi.getDeXuatById(id).enqueue(new Callback<CTLKDoanhNghiep>() {
            @Override
            public void onResponse(Call<CTLKDoanhNghiep> call, Response<CTLKDoanhNghiep> response) {
                CTLKDoanhNghiep ctlkDoanhNghiep = response.body();
                Intent intent = new Intent(context, FormPhanHoiDeXuatChuongTrinhLienKet.class);
                intent.putExtra("chuongtrinhlienket", (Serializable) ctlkDoanhNghiep);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<CTLKDoanhNghiep> call, Throwable t) {
                Toast.makeText(context, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
                Log.d("ddgfffffffffffff", t.getMessage());

            }
        });


    }
    public void getChuongTrinhLienKetByIdXemChiTiet(int id){
        ChuongTrinhLienKetApi.chuongTrinhLienKetApi.getDeXuatById(id).enqueue(new Callback<CTLKDoanhNghiep>() {
            @Override
            public void onResponse(Call<CTLKDoanhNghiep> call, Response<CTLKDoanhNghiep> response) {
                CTLKDoanhNghiep ctlkDoanhNghiep = response.body();
                Intent intent = new Intent(context, XemChiTietChuongTrinhLienKet.class);
                intent.putExtra("xemchitietchuongtrinhlienket", (Serializable) ctlkDoanhNghiep);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<CTLKDoanhNghiep> call, Throwable t) {
                Toast.makeText(context, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
                Log.d("ddgfffffffffffff", t.getMessage());

            }
        });


    }


    public void confirmDialog(){
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("XÓA ĐỀ XUẤT CHƯƠNG TRÌNH LIÊN KẾT");
        builder.setMessage("Bạn muốn xóa doanh nghiệp này?");

        builder.setPositiveButton("Đúng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                xoaChuongTrinh(idChuongTrinh);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((QuanLyChuongTrinhLienKet)context).clickCallApi();
                    }
                },1000);
                Toast.makeText(context, "Đã xóa chương trình đề xuất đã chọn", Toast.LENGTH_LONG).show();




            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    public void xoaChuongTrinh(int id){
        ChuongTrinhLienKetApi.chuongTrinhLienKetApi.xoaDeXuat(id).enqueue(new Callback<CTLKDoanhNghiep>() {
            @Override
            public void onResponse(Call<CTLKDoanhNghiep> call, Response<CTLKDoanhNghiep> response) {

            }

            @Override
            public void onFailure(Call<CTLKDoanhNghiep> call, Throwable t) {

            }
        });




    }
}
