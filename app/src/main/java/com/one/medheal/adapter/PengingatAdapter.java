package com.one.medheal.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.medheal.EditPengingatObatActivity;
import com.one.medheal.R;
import com.one.medheal.database.Obat;
import com.one.medheal.database.PengingatObat;

import java.util.ArrayList;

public class PengingatAdapter extends RecyclerView.Adapter<PengingatAdapter.ViewHolder>{

    private LayoutInflater mInflater;
    private ArrayList<PengingatObat> listPengingat;
    Context context;

    public PengingatAdapter(ArrayList<PengingatObat> list, Context context){
        this.listPengingat = list;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public PengingatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_pengingat_obat, parent, false);
        return new PengingatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PengingatAdapter.ViewHolder holder, int position) {
        PengingatObat data = listPengingat.get(position);

        holder.tvNamaObat.setText(data.getNamaObat());
        holder.tvWaktu.setText(data.getWaktu());
        holder.tvFrekuensi.setText(data.getFrekuensi());
        holder.tvDurasi.setText(data.getDurasi());
        holder.tvInventaris.setText(data.getInventaris());
        holder.tvCatatan.setText(data.getCatatan());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, EditPengingatObatActivity.class);
            intent.putExtra("pengingat", data);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return listPengingat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNamaObat;
        private TextView tvWaktu;
        private TextView tvFrekuensi;
        private TextView tvDurasi;
        private TextView tvInventaris;
        private TextView tvCatatan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaObat = itemView.findViewById(R.id.tvNamaObat);
            tvWaktu = itemView.findViewById(R.id.tvWaktu);
            tvFrekuensi = itemView.findViewById(R.id.tvFrek);
            tvDurasi = itemView.findViewById(R.id.tvDurasi);
            tvInventaris = itemView.findViewById(R.id.tvInventaris);
            tvCatatan = itemView.findViewById(R.id.tvCatatan);

        }
    }
}
