package com.one.medheal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.one.medheal.adapter.ObatRvAdapter;
import com.one.medheal.model.Obat;

import java.util.ArrayList;

public class DrugActivity extends AppCompatActivity {

    boolean visible = true;

    private RecyclerView rvObat;
    private RecyclerView rvObatFav;
    private ArrayList<Obat> listObat;
    private ObatRvAdapter obatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavBar);
        LinearLayout bottomSubMenu = findViewById(R.id.bottomSubmenu);
        LinearLayout btnSymptom = findViewById(R.id.btnSymptom);

        rvObat = findViewById(R.id.rvObat);
        rvObatFav = findViewById(R.id.rvObatku);
        listObat = new ArrayList<Obat>();

        init();

        bottomNavigationView.setSelectedItemId(R.id.drug);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){
                case R.id.home:
                    Intent intent = new Intent(DrugActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case R.id.drug:
                    Intent intent2 = new Intent(DrugActivity.this, DrugActivity.class);
                    intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent2);
                    break;
                case R.id.track:
                    if (visible){
                        bottomSubMenu.setVisibility(View.VISIBLE);
                        visible = false;
                    } else {
                        bottomSubMenu.setVisibility(View.GONE);
                        visible = true;
                    }
                    break;
            }

            return false;
        });

        btnSymptom.setOnClickListener(view -> {
            Intent intent = new Intent(DrugActivity.this, SymptomActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        showObat();
    }

    private void showObat(){
        obatAdapter = new ObatRvAdapter(listObat, this, false);
        rvObat.setLayoutManager(new GridLayoutManager(this, 2));
        rvObat.setAdapter(obatAdapter);
    }

    private void init(){
        listObat.add(new Obat("Paracetamol", "20.000", "gatau", "gatau",
                "gatau", false, R.drawable.animasi));
        listObat.add(new Obat("Paracetamol", "20.000", "gatau", "gatau",
                "gatau", false, R.drawable.animasi));
        listObat.add(new Obat("Paracetamol", "20.000", "gatau", "gatau",
                "gatau", false, R.drawable.animasi));
        listObat.add(new Obat("Paracetamol", "20.000", "gatau", "gatau",
                "gatau", false, R.drawable.animasi));
    }


}