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

import com.example.expendlistview.API.DeTaiNghienCuuApi;
import com.example.expendlistview.API.TaiLieuNghienCuuApi;
import com.example.expendlistview.Activity.FormPheDuyetDeTai;
import com.example.expendlistview.Activity.PheDuyetDeTai;
import com.example.expendlistview.Activity.QuanLyTaiLieu;
import com.example.expendlistview.Activity.SuaVaXoaTaiLieuNghienCuu;
import com.example.expendlistview.Model.DeTaiNghienCuu;
import com.example.expendlistview.Model.TaiLieuNghienCuu;
import com.example.expendlistview.R;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PheDuyetDeTaiAdapter  extends RecyclerView.Adapter<PheDuyetDeTaiAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private List<DeTaiNghienCuu> listDeTaiNghienCuu;
    int idDeTai;
    DeTaiNghienCuu deTaiNghienCuu;


    public PheDuyetDeTaiAdapter(Context context, Activity activity, List<DeTaiNghienCuu> listDeTaiNghienCuu) {
        this.context = context;
        this.activity = activity;
        this.listDeTaiNghienCuu = listDeTaiNghienCuu;
    }

    @NonNull
    @Override
    public PheDuyetDeTaiAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_de_tai_nghien_cuu,parent,false);
        return new PheDuyetDeTaiAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PheDuyetDeTaiAdapter.MyViewHolder holder, int position) {

        DeTaiNghienCuu deTaiNghienCuu =listDeTaiNghienCuu.get(position);
        holder.id_txt.setText(String.valueOf(deTaiNghienCuu.getId()));
        holder.hoVaTen_txt.setText(String.valueOf(deTaiNghienCuu.getHoVaTen()));
        holder.maSV_txt.setText(String.valueOf(deTaiNghienCuu.getMaSV()));
        holder.tenDeTai_txt.setText(String.valueOf(deTaiNghienCuu.getTenDeTai()));
        holder.trangThai_txt.setText(String.valueOf(deTaiNghienCuu.getTrangThaiDuyet()));
        holder.thoiGianDuKien_txt.setText(String.valueOf(deTaiNghienCuu.getThoiGianDuKien()));
        holder.kinhPhi_txt.setText(String.valueOf(deTaiNghienCuu.getKinhPhi()));
        holder.ghiChu_txt.setText(String.valueOf(deTaiNghienCuu.getGhiChu()));
        holder.edit_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Nhấn edit", Toast.LENGTH_LONG).show();
                // code phe duyet de tai
                idDeTai = Integer.parseInt((String)holder.id_txt.getText());
                getDeTaiByID(idDeTai);
            }
        });
        holder.delete_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // code xoa de tai
                idDeTai= Integer.parseInt((String)holder.id_txt.getText());
                confirmDialog();
            }
        });




    }

    @Override
    public int getItemCount() {
        return listDeTaiNghienCuu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_txt, hoVaTen_txt, maSV_txt, tenDeTai_txt, thoiGianDuKien_txt, kinhPhi_txt, ghiChu_txt, trangThai_txt;
        ImageView edit_image, delete_image;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id_txt = itemView.findViewById(R.id.id_txt);
            hoVaTen_txt = itemView.findViewById(R.id.hovaten_txt);
            maSV_txt = itemView.findViewById(R.id.masv_txt);
            tenDeTai_txt = itemView.findViewById(R.id.tendetai_txt);
            trangThai_txt = itemView.findViewById(R.id.trangthai_txt);
            thoiGianDuKien_txt = itemView.findViewById(R.id.thoigian_txt);
            kinhPhi_txt = itemView.findViewById(R.id.kinhphi_txt);
            ghiChu_txt =itemView.findViewById(R.id.ghichu_txt);
            edit_image = itemView.findViewById(R.id.edit);
            delete_image = itemView.findViewById(R.id.delete);





        }
    }


    public void getDeTaiByID(int id){
        DeTaiNghienCuuApi.deTaiNghienCuuApi.getDeTaiById(id).enqueue(new Callback<DeTaiNghienCuu>() {
            @Override
            public void onResponse(Call<DeTaiNghienCuu> call, Response<DeTaiNghienCuu> response) {
                DeTaiNghienCuu deTaiNghienCuu = response.body();
                Intent intent = new Intent(context, FormPheDuyetDeTai.class);
                intent.putExtra("detai", (Serializable) deTaiNghienCuu);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<DeTaiNghienCuu> call, Throwable t) {
                Toast.makeText(context, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
                Log.d("ddgfffffffffffff", t.getMessage());

            }
        });


    }

    public void confirmDialog(){
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("XÓA TÀI LIỆU");
        builder.setMessage("Bạn muốn xóa đề tài này?");

        builder.setPositiveButton("Đúng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteDeTai(idDeTai);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((PheDuyetDeTai)context).clickCallApi();
                    }
                },1000);
                Toast.makeText(context, "Xóa đề tài thành công", Toast.LENGTH_LONG).show();




            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    public void deleteDeTai(int id){
        DeTaiNghienCuuApi.deTaiNghienCuuApi.deleteDeTai(id).enqueue(new Callback<DeTaiNghienCuu>() {
            @Override
            public void onResponse(Call<DeTaiNghienCuu> call, Response<DeTaiNghienCuu> response) {

            }

            @Override
            public void onFailure(Call<DeTaiNghienCuu> call, Throwable t) {

            }
        });



    }
}