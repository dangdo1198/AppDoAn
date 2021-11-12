package com.example.expendlistview.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expendlistview.API.TaiLieuNghienCuuApi;
import com.example.expendlistview.Activity.SuaVaXoaTaiLieuNghienCuu;
import com.example.expendlistview.Model.TaiLieuNghienCuu;
import com.example.expendlistview.R;


import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuanLyTaiLieuAdapter extends RecyclerView.Adapter<QuanLyTaiLieuAdapter.MyViewHolder> {
    private Context context;

    Activity activity;
    private List<TaiLieuNghienCuu> listTaiLieuNghienCuu;
    int idTaiLieu;


    public QuanLyTaiLieuAdapter(Context context, Activity activity, List<TaiLieuNghienCuu> listTaiLieuNghienCuu) {
        this.context = context;
        this.activity = activity;
        this.listTaiLieuNghienCuu = listTaiLieuNghienCuu;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_tai_lieu_nghien_cuu,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TaiLieuNghienCuu taiLieuNghienCuu = listTaiLieuNghienCuu.get(position);
        holder.id_txt.setText(String.valueOf(taiLieuNghienCuu.getId()));
        holder.tacGia_txt.setText(taiLieuNghienCuu.getTacGia());
        holder.tenTaiLieu_txt.setText(taiLieuNghienCuu.getTenTaiLieu());
        holder.linhVuc_txt.setText(taiLieuNghienCuu.getLinhVuc());
        holder.thoiGianDuKien_txt.setText(taiLieuNghienCuu.getThoiGian());
        holder.link_btn.setText("Xem chi tiết");

        holder.link_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // cách intent một button trong adapter recyclerView
                // Thêm thẻ data trong mainifesst sau đó đặt intent trong onclick listenview ở phần holder.
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.epub.vn/categories"));
                context.startActivity(intent);
            }
        });
//        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                idTaiLieu = Integer.parseInt((String)holder.id_txt.getText());
//                getByIdData(idTaiLieu);
//
//
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return listTaiLieuNghienCuu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_txt, tenTaiLieu_txt, tacGia_txt, linhVuc_txt, thoiGianDuKien_txt;
        Button link_btn;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id_txt);
            tenTaiLieu_txt = itemView.findViewById(R.id.tentailieu_txt);
            tacGia_txt = itemView.findViewById(R.id.tacgia_txt);
            linhVuc_txt = itemView.findViewById(R.id.linhvuc_txt);
            thoiGianDuKien_txt = itemView.findViewById(R.id.thoigian_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            link_btn = itemView.findViewById(R.id.btn_xemthem);

            mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    idTaiLieu = Integer.parseInt((String)id_txt.getText());
                getByIdData(idTaiLieu);
                }
            });



        }
    }



    public void getByIdData(int id){
        TaiLieuNghienCuuApi.taiLieuNghienCuuApi.getTaiLieuById(id).enqueue(new Callback<TaiLieuNghienCuu>() {
            @Override
            public void onResponse(Call<TaiLieuNghienCuu> call, Response<TaiLieuNghienCuu> response) {
                TaiLieuNghienCuu taiLieuNghienCuu = response.body();
                Intent intent = new Intent(context,SuaVaXoaTaiLieuNghienCuu.class);
               intent.putExtra("tailing", (Serializable) taiLieuNghienCuu);
//                intent.putExtra("tentailieu",String.valueOf(taiLieuNghienCuu.getTenTaiLieu()));
//                intent.putExtra("tacgia",String.valueOf(taiLieuNghienCuu.getTacGia()));
//                intent.putExtra("linhvuc",String.valueOf(taiLieuNghienCuu.getLinhVuc()));
//                intent.putExtra("thoigian",String.valueOf(taiLieuNghienCuu.getThoiGian()));
//                intent.putExtra("linktai",String.valueOf(taiLieuNghienCuu.getLinkTai()));
                context.startActivity(intent);
                Toast.makeText(context, "Đã chọn tài liệu " +  taiLieuNghienCuu.getTenTaiLieu() , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<TaiLieuNghienCuu> call, Throwable t) {
                Toast.makeText(context, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
                Log.d("ddgfffffffffffff", t.getMessage());

            }
        });


    }
}
