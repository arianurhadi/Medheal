package com.one.medheal.drug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.one.medheal.DrinkActivity;
import com.one.medheal.HomeActivity;
import com.one.medheal.drugreminder.PengingatObatActivity;
import com.one.medheal.R;
import com.one.medheal.SearchActivity;
import com.one.medheal.sleep.SleepActivity;
import com.one.medheal.adapter.ObatRvAdapter;
import com.one.medheal.database.Database;
import com.one.medheal.database.Obat;
import com.one.medheal.symptom.SymptomActivity;

import java.util.ArrayList;

public class DrugActivity extends AppCompatActivity {

    boolean visible = true;

    private BottomNavigationView bottomNavigationView;
    private LinearLayout bottomSubMenu;
    private LinearLayout btnSymptom;
    private LinearLayout btnDrink;
    private LinearLayout btnSleep;

    private RecyclerView rvObat;
    private RecyclerView rvObatFav;
    private ArrayList<Obat> listObat;
    private ArrayList<Obat> listFavObat;
    private ObatRvAdapter obatAdapter;
    private ObatRvAdapter favObatAdapter;
    private Database db;

    private TextView tvJmlObat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);

        bottomNavigationView = findViewById(R.id.bottomNavBar);
        bottomSubMenu = findViewById(R.id.bottomSubmenu);
        btnSymptom = findViewById(R.id.btnSymptom);
        btnDrink = findViewById(R.id.btnDrink);
        btnSleep = findViewById(R.id.btnSleep);

        TextView tvBelumAdaFav = findViewById(R.id.tvBelumAda);
        tvJmlObat = findViewById(R.id.tvJmlObat);
        db = Database.getInstance(this);

        rvObat = findViewById(R.id.rvObat);
        rvObatFav = findViewById(R.id.rvObatku);
        listObat = new ArrayList<Obat>();
        listFavObat = new ArrayList<Obat>();

//        init();
        listObat = (ArrayList<Obat>) db.obatDao().getAllObat();
        filterFavObat();

        bottomNavigationView.setSelectedItemId(R.id.drug);

        tvJmlObat.setText(listObat.size() + " Obat");

        bottomNav();

        showObat();

        if (listFavObat.isEmpty()){
            rvObatFav.setVisibility(View.GONE);
            tvBelumAdaFav.setVisibility(View.VISIBLE);
        }else {
            tvBelumAdaFav.setVisibility(View.GONE);
            rvObatFav.setVisibility(View.VISIBLE);
            showFavorite();
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private void showObat(){
        obatAdapter = new ObatRvAdapter(listObat, this, false);
        rvObat.setLayoutManager(new GridLayoutManager(this, 2));
        rvObat.setAdapter(obatAdapter);
        obatAdapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void showFavorite(){
        favObatAdapter = new ObatRvAdapter(listFavObat, this, true);
        rvObatFav.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rvObatFav.setAdapter(favObatAdapter);
        favObatAdapter.notifyDataSetChanged();
    }

    private void filterFavObat(){
        for (Obat data : listObat) {
            if (data.getFavObat()){
                listFavObat.add(data);
            }
        }
    }

    private void bottomNav(){
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){
                case R.id.home:
                    Intent intentHome = new Intent(DrugActivity.this, HomeActivity.class);
                    intentHome.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentHome);
                    break;
                case R.id.search:
                    Intent intentSearch = new Intent(DrugActivity.this, SearchActivity.class);
                    intentSearch.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentSearch);
                    break;
                case R.id.drug:
                    Intent intentDrug = new Intent(DrugActivity.this, DrugActivity.class);
                    intentDrug.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentDrug);
                    break;
                case R.id.reminder:
                    Intent intentReminder = new Intent(DrugActivity.this, PengingatObatActivity.class);
                    intentReminder.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentReminder);
                    break;
                case R.id.track:
                    if (visible){
                        bottomSubMenu.setVisibility(View.VISIBLE);
                        bottomSubMenu.animate().translationY(20);
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

        btnDrink.setOnClickListener(view -> {
            Intent intent = new Intent(DrugActivity.this, DrinkActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        btnSleep.setOnClickListener(view -> {
            Intent intent = new Intent(DrugActivity.this, SleepActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });
    }

}