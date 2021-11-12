package com.example.expendlistview.Adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.expendlistview.Model.DeTaiNghienCuu;
import com.example.expendlistview.R;
import java.util.List;
public class DeTaiDaDangKyAdapter extends RecyclerView.Adapter<DeTaiDaDangKyAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private List<DeTaiNghienCuu> listDeTaiNghienCuu;
//    DeTaiNghienCuu deTaiNghienCuu;


    public DeTaiDaDangKyAdapter(Context context, Activity activity, List<DeTaiNghienCuu> listDeTaiNghienCuu) {
        this.context = context;
        this.activity = activity;
        this.listDeTaiNghienCuu = listDeTaiNghienCuu;
    }

    @NonNull
    @Override
    public DeTaiDaDangKyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_de_tai_da_dang_ky,parent,false);
        return new DeTaiDaDangKyAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeTaiDaDangKyAdapter.MyViewHolder holder, int position) {

        DeTaiNghienCuu deTaiNghienCuu =listDeTaiNghienCuu.get(position);
        holder.id_txt.setText(String.valueOf(deTaiNghienCuu.getId()));
        holder.tenDeTai_txt.setText(String.valueOf(deTaiNghienCuu.getTenDeTai()));
        holder.thoiGianDuKien_txt.setText(String.valueOf(deTaiNghienCuu.getThoiGianDuKien()));
        holder.kinhPhi_txt.setText(String.valueOf(deTaiNghienCuu.getKinhPhi()));
        holder.linhVuc_txt.setText(String.valueOf(deTaiNghienCuu.getLinhVuc()));
    }

    @Override
    public int getItemCount() {
        return listDeTaiNghienCuu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_txt, linhVuc_txt, tenDeTai_txt, thoiGianDuKien_txt, kinhPhi_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id_txt);
            tenDeTai_txt = itemView.findViewById(R.id.tendetai_txt);
            linhVuc_txt = itemView.findViewById(R.id.linhvuc_txt);
            thoiGianDuKien_txt = itemView.findViewById(R.id.thoigian_txt);
            kinhPhi_txt = itemView.findViewById(R.id.kinhphi_txt);
        }
    }

}
