package com.example.expendlistview.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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


import com.example.expendlistview.API.DoanhNghiepHopTacApi;

import com.example.expendlistview.Activity.DanhSachDoanhNghiep;
import com.example.expendlistview.Activity.FormCapNhatDoanhNghiep;
import com.example.expendlistview.Model.DoanhNghiepHopTac;
import com.example.expendlistview.R;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachDoanhNghiepAdapter extends RecyclerView.Adapter<DanhSachDoanhNghiepAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private List<DoanhNghiepHopTac> listDoanhNghiepHopTac;
    int idDoanhNghiep;
    DoanhNghiepHopTac doanhNghiepHopTac;


    public DanhSachDoanhNghiepAdapter(Context context, Activity activity, List<DoanhNghiepHopTac> listDoanhNghiepHopTac ) {
        this.context = context;
        this.activity = activity;
        this.listDoanhNghiepHopTac = listDoanhNghiepHopTac;
    }

    @NonNull
    @Override
    public DanhSachDoanhNghiepAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_doanh_nghiep,parent,false);
        return new DanhSachDoanhNghiepAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhSachDoanhNghiepAdapter.MyViewHolder holder, int position) {

        DoanhNghiepHopTac doanhNghiepHopTac = listDoanhNghiepHopTac.get(position);
        holder.idTxt.setText(String.valueOf(doanhNghiepHopTac.getId()));
        holder.doanhNghiepTxt.setText(String.valueOf(doanhNghiepHopTac.getTenDoanhNghiep()));
        holder.giamDocTxt.setText(String.valueOf(doanhNghiepHopTac.getTenGiamDoc()));
        holder.linhVucTxt.setText(String.valueOf(doanhNghiepHopTac.getLinhVuc()));
        holder.namHopTacTxt.setText(String.valueOf(doanhNghiepHopTac.getNamHopTac()));
        holder.truSoTxt.setText(String.valueOf(doanhNghiepHopTac.getTruSoChinh()));
        holder.hotlineTxt.setText(String.valueOf(doanhNghiepHopTac.getSoDienThoai()));

        holder.updateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Nhấn edit", Toast.LENGTH_LONG).show();
                // code phe duyet de tai
                idDoanhNghiep = Integer.parseInt((String)holder.idTxt.getText());
                getDoanhNghiepById(idDoanhNghiep);
            }
        });
        holder.deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // code xoa de tai
                idDoanhNghiep= Integer.parseInt((String)holder.idTxt.getText());
                confirmDialog();
            }
        });
        holder.linkWebsiteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(String.valueOf(doanhNghiepHopTac.getWebsite())));
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return listDoanhNghiepHopTac.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView idTxt, doanhNghiepTxt, giamDocTxt, linhVucTxt, truSoTxt, namHopTacTxt, hotlineTxt;
        ImageView updateImg, deleteImg, linkWebsiteImg;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            idTxt = itemView.findViewById(R.id.id_txt);
            giamDocTxt = itemView.findViewById(R.id.giamdoc_txt);
            doanhNghiepTxt = itemView.findViewById(R.id.doanhnghiep_txt);
            linhVucTxt = itemView.findViewById(R.id.linhvuc_txt);
            truSoTxt = itemView.findViewById(R.id.truso_txt);
            namHopTacTxt = itemView.findViewById(R.id.namhoptac_txt);
            hotlineTxt = itemView.findViewById(R.id.hotline_txt);
            updateImg = itemView.findViewById(R.id.update_img);
            deleteImg = itemView.findViewById(R.id.delete_img);
            linkWebsiteImg = itemView.findViewById(R.id.linkweb_img);






        }
    }


    public void getDoanhNghiepById(int id){
        DoanhNghiepHopTacApi.doanhNghiepHopTacApi.getDoanhNghiepById(id).enqueue(new Callback<DoanhNghiepHopTac>() {
            @Override
            public void onResponse(Call<DoanhNghiepHopTac> call, Response<DoanhNghiepHopTac> response) {
                DoanhNghiepHopTac doanhNghiepHopTac = response.body();
                Intent intent = new Intent(context, FormCapNhatDoanhNghiep.class);
                intent.putExtra("doanhnghiep", (Serializable) doanhNghiepHopTac);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<DoanhNghiepHopTac> call, Throwable t) {
                Toast.makeText(context, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
                Log.d("ddgfffffffffffff", t.getMessage());

            }
        });



    }

    public void confirmDialog(){
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("XÓA DOANH NGHIỆP");
        builder.setMessage("Bạn muốn xóa doanh nghiệp này?");

        builder.setPositiveButton("Đúng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteDoanhNghiep(idDoanhNghiep);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((DanhSachDoanhNghiep)context).clickCallApi();
                    }
                },1000);
                Toast.makeText(context, "Xóa doanh nghiệp thành công", Toast.LENGTH_LONG).show();




            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    public void deleteDoanhNghiep(int id){
        DoanhNghiepHopTacApi.doanhNghiepHopTacApi.deleteDoanhNghiep(id).enqueue(new Callback<DoanhNghiepHopTac>() {
            @Override
            public void onResponse(Call<DoanhNghiepHopTac> call, Response<DoanhNghiepHopTac> response) {

            }

            @Override
            public void onFailure(Call<DoanhNghiepHopTac> call, Throwable t) {

            }
        });



    }
}
