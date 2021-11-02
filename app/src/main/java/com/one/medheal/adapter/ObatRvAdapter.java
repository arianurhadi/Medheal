package com.one.medheal.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.medheal.DetailDrugActivity;
import com.one.medheal.R;
import com.one.medheal.database.Obat;
import com.one.medheal.model.ObatModel;

import java.util.ArrayList;

public class ObatRvAdapter extends RecyclerView.Adapter<ObatRvAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<Obat> listObat;
    Context context;
    boolean fav;

    public ObatRvAdapter(ArrayList<Obat> list, Context context, boolean fav){
        this.listObat = list;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.fav = fav;
    }

    public void setList(ArrayList<Obat> list){
        listObat.clear();
        this.listObat = list;
    }

    @NonNull
    @Override
    public ObatRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_obat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObatRvAdapter.ViewHolder holder, int position) {

        Obat obat = listObat.get(position);

        holder.namaObat.setText(obat.getNamaObat());
        holder.gambarObat.setImageResource(obat.getGambarObat());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailDrugActivity.class);
            intent.putExtra("obat", obat);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return listObat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaObat;
        ImageView gambarObat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namaObat = itemView.findViewById(R.id.tvObat);
            gambarObat = itemView.findViewById(R.id.imgObat);

        }
    }
}
