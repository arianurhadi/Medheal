package com.one.medheal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.one.medheal.database.Database;
import com.one.medheal.database.Obat;
import com.one.medheal.database.ObatDao;

public class DetailDrugActivity extends AppCompatActivity {
    TextView namaObat;
    TextView hargaObat;
    TextView indikasi;
    TextView kontra;
    TextView efek;
    ImageView gambarObat;
    ImageView btnFav;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_drug);

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> {
            view.setSelected(true);
            startActivity(new Intent(DetailDrugActivity.this, DrugActivity.class));
            finish();
        });

        Obat obat = (Obat) getIntent().getSerializableExtra("obat");
//        int position = getIntent().getIntExtra("position", 0);

        namaObat = findViewById(R.id.tvObat);
        hargaObat = findViewById(R.id.tvHarga);
        indikasi = findViewById(R.id.tvIndikasi);
        kontra = findViewById(R.id.tvKontra);
        efek = findViewById(R.id.tvEfek);
        gambarObat = findViewById(R.id.imgObat);
        btnFav = findViewById(R.id.btnFav);

        db = Database.getInstance(this);

        namaObat.setText(obat.getNamaObat());
        hargaObat.setText(obat.getHargaObat());
        indikasi.setText(obat.getIndikasiObat());
        kontra.setText(obat.getKontraObat());
        efek.setText(obat.getEfekObat());
        gambarObat.setImageResource(obat.getGambarObat());

        String idObat = obat.getIdObat();
        boolean obatFav = obat.getFavObat();

        if (obatFav){
            btnFav.setImageResource(R.drawable.ic_love);
        }else{
            btnFav.setImageResource(R.drawable.ic_love_outline);
        }

        ObatDao obatDao = db.obatDao();

        btnFav.setOnClickListener(view -> {
            if (!obatFav){
                btnFav.setImageResource(R.drawable.ic_love);
                obatDao.updateFavObat(true, idObat);
            } else {
                btnFav.setImageResource(R.drawable.ic_love_outline);
                obatDao.updateFavObat(false, idObat);
            }
        });

    }
}