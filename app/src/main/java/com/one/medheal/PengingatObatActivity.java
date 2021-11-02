package com.one.medheal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.one.medheal.adapter.PengingatAdapter;
import com.one.medheal.database.Database;
import com.one.medheal.database.PengingatObat;
import com.one.medheal.database.PengingatObatDao;

import java.util.ArrayList;
import java.util.List;

public class PengingatObatActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private LinearLayout bottomSubMenu;
    private LinearLayout btnSymptom;
    private LinearLayout btnDrink;
    private LinearLayout btnSleep;
    private LinearLayout sectAlert;

    private ImageView btnTambah;

    private RecyclerView rcView;
    private PengingatAdapter pengingatAdapter;
    private Database db;

    ArrayList<PengingatObat> listPengingat;
    boolean visible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pengingat_minum_obat);

        bottomNavigationView = findViewById(R.id.bottomNavBar);
        bottomSubMenu = findViewById(R.id.bottomSubmenu);
        btnSymptom = findViewById(R.id.btnSymptom);
        btnDrink = findViewById(R.id.btnDrink);
        btnSleep = findViewById(R.id.btnSleep);
        sectAlert = findViewById(R.id.sectAlert);
        btnTambah = findViewById(R.id.btnTambah);

        db = Database.getInstance(this);
        rcView = findViewById(R.id.rvReminderObat);

        bottomNavigationView.setSelectedItemId(R.id.reminder);

        listPengingat = new ArrayList<PengingatObat>();

        bottomNav();
        
        getData();

        if (listPengingat.isEmpty()){
            sectAlert.setVisibility(View.VISIBLE);
            rcView.setVisibility(View.GONE);
        } else {
            sectAlert.setVisibility(View.GONE);
            rcView.setVisibility(View.VISIBLE);
        }
        
        showRcView();

        btnTambah.setOnClickListener(view -> {
            startActivity(new Intent(PengingatObatActivity.this, SetPengingatObatActivity.class));
        });

    }

    private void getData(){
        PengingatObatDao pengingatObatDao = db.pengingatObatDao();

        listPengingat = (ArrayList<PengingatObat>) pengingatObatDao.getAllPengingat();
    }

    private void showRcView(){
        pengingatAdapter = new PengingatAdapter(listPengingat, this);
        pengingatAdapter.notifyDataSetChanged();
        rcView.setLayoutManager(new LinearLayoutManager(this));
        rcView.setAdapter(pengingatAdapter);
    }

    private void bottomNav(){
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){
                case R.id.home:
                    Intent intentHome = new Intent(PengingatObatActivity.this, HomeActivity.class);
                    intentHome.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentHome);
                    break;
                case R.id.search:
                    Intent intentSearch = new Intent(PengingatObatActivity.this, SearchActivity.class);
                    intentSearch.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentSearch);
                    break;
                case R.id.drug:
                    Intent intentDrug = new Intent(PengingatObatActivity.this, DrugActivity.class);
                    intentDrug.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentDrug);
                    break;
                case R.id.reminder:
                    Intent intentReminder = new Intent(PengingatObatActivity.this, PengingatObatActivity.class);
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
            Intent intent = new Intent(PengingatObatActivity.this, SymptomActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        btnDrink.setOnClickListener(view -> {
            Intent intent = new Intent(PengingatObatActivity.this, DrinkActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        btnSleep.setOnClickListener(view -> {
            Intent intent = new Intent(PengingatObatActivity.this, SleepActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });
    }

}