package com.one.medheal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.one.medheal.model.Obat;

public class DetailDrugActivity extends AppCompatActivity {
    TextView namaObat;
    TextView hargaObat;
    TextView indikasi;
    TextView kontra;
    TextView efek;
    ImageView gambarObat;
    ImageView imgFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_drug);

        Obat obat = (Obat) getIntent().getSerializableExtra("obat");

        namaObat = findViewById(R.id.tvObat);
        hargaObat = findViewById(R.id.tvHarga);
        indikasi = findViewById(R.id.tvIndikasi);
        kontra = findViewById(R.id.tvKontra);
        efek = findViewById(R.id.tvEfek);
        gambarObat = findViewById(R.id.imgObat);
        imgFav = findViewById(R.id.imgLove);

        namaObat.setText(obat.getNamaObat());
        hargaObat.setText(obat.getHarga());
        indikasi.setText(obat.getIndikasi());
        kontra.setText(obat.getKontra());
        efek.setText(obat.getEfek());
        gambarObat.setImageResource(obat.getGambarObat());

//        if (obat.getFav()){
//            imgFav.setImageResource(R.drawable.ic_love);
//        }else{
//            imgFav.setImageResource(R.drawable.ic_love_outline);
//        }

    }
}