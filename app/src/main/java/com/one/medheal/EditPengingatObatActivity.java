package com.one.medheal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.one.medheal.database.Database;
import com.one.medheal.database.PengingatObat;

public class EditPengingatObatActivity extends AppCompatActivity {

    private EditText inputNamaObat;
    private EditText inputWaktu;
    private EditText inputFrekuensi;
    private EditText inputInventaris;
    private EditText inputDurasi;
    private EditText inputCatatan;

    private Database db;

    private AppCompatButton btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pengingat_obat);

        inputNamaObat = findViewById(R.id.inputNamaObat);
        inputWaktu = findViewById(R.id.inputWaktu);
        inputFrekuensi = findViewById(R.id.inputFrekuensi);
        inputInventaris = findViewById(R.id.inputInventaris);
        inputDurasi = findViewById(R.id.inputDurasi);
        inputCatatan = findViewById(R.id.inputCatatan);

        db = Database.getInstance(this);

        btnSimpan = findViewById(R.id.btnSimpan);

        PengingatObat data = (PengingatObat) getIntent().getSerializableExtra("pengingat");

        inputNamaObat.setText(data.getNamaObat());
        inputWaktu.setText(data.getWaktu());
        inputFrekuensi.setText(data.getFrekuensi());
        inputDurasi.setText(data.getDurasi());
        inputInventaris.setText(data.getInventaris());
        inputCatatan.setText(data.getCatatan());

        btnSimpan.setOnClickListener(view -> {

            String namaObat = inputNamaObat.getText().toString().trim();
            String waktu = inputWaktu.getText().toString().trim();
            String frekuensi = inputFrekuensi.getText().toString().trim();
            String inventaris = inputInventaris.getText().toString().trim();
            String durasi = inputDurasi.getText().toString().trim();
            String catatan = inputCatatan.getText().toString().trim();

            PengingatObat pengingatObat = new PengingatObat(namaObat, waktu, frekuensi, inventaris, durasi, catatan, true);

            db.pengingatObatDao().updatePengingat(pengingatObat);

            startActivity(new Intent(EditPengingatObatActivity.this, PengingatObatActivity.class));
            finish();

        });

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });

    }
}