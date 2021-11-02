package com.one.medheal.drugreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.one.medheal.R;
import com.one.medheal.database.Database;
import com.one.medheal.database.PengingatObat;

public class SetPengingatObatActivity extends AppCompatActivity {

    private EditText inputNamaObat;
    private EditText inputWaktu;
    private EditText inputFrekuensi;
    private EditText inputInventaris;
    private EditText inputDurasi;
    private EditText inputCatatan;

    private AppCompatButton btnSimpan;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pengingat_obat);

        inputNamaObat = findViewById(R.id.inputNamaObat);
        inputWaktu = findViewById(R.id.inputWaktu);
        inputFrekuensi = findViewById(R.id.inputFrekuensi);
        inputInventaris = findViewById(R.id.inputInventaris);
        inputDurasi = findViewById(R.id.inputDurasi);
        inputCatatan = findViewById(R.id.inputCatatan);

        btnSimpan = findViewById(R.id.btnSimpan);

        db = Database.getInstance(this);

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        btnSimpan.setOnClickListener(view -> {

            if (!validate()){
                return;
            }

            String namaObat = inputNamaObat.getText().toString().trim();
            String waktu = inputWaktu.getText().toString().trim();
            String frekuensi = inputFrekuensi.getText().toString().trim();
            String inventaris = inputInventaris.getText().toString().trim();
            String durasi = inputDurasi.getText().toString().trim();
            String catatan = inputCatatan.getText().toString().trim();

            PengingatObat pengingatObat = new PengingatObat(namaObat, waktu, frekuensi, inventaris, durasi, catatan, true);

            db.pengingatObatDao().insertPengingat(pengingatObat);

            startActivity(new Intent(SetPengingatObatActivity.this, PengingatObatActivity.class));
            finish();

        });

    }

    private boolean validate(){
        boolean validate = true;

        if (TextUtils.isEmpty(inputNamaObat.getText().toString())){
            inputNamaObat.setError("Nama obat harus di isi");
            validate = false;
        }
        if (TextUtils.isEmpty(inputWaktu.getText().toString())){
            inputWaktu.setError("Waktu harus di isi");
            validate = false;
        }
        if (TextUtils.isEmpty(inputFrekuensi.getText().toString())){
            inputFrekuensi.setError("Frekuensi harus di isi");
            validate = false;
        }
        if (TextUtils.isEmpty(inputInventaris.getText().toString())){
            inputInventaris.setError("Inventaris harus di isi");
            validate = false;
        }
        if (TextUtils.isEmpty(inputDurasi.getText().toString())){
            inputDurasi.setError("Durasi harus di isi");
            validate = false;
        }

        return validate;
    }

}