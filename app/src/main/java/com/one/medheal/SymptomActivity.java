package com.one.medheal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SymptomActivity extends AppCompatActivity {

    boolean visible = true;

    private BottomNavigationView bottomNavigationView;
    private LinearLayout bottomSubMenu;
    private LinearLayout btnSymptom;
    private LinearLayout btnDrink;
    private LinearLayout btnSleep;

    private ImageView btnTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom);

        bottomNavigationView = findViewById(R.id.bottomNavBar);
        bottomSubMenu = findViewById(R.id.bottomSubmenu);
        btnSymptom = findViewById(R.id.btnSymptom);
        btnDrink = findViewById(R.id.btnDrink);
        btnSleep = findViewById(R.id.btnSleep);

        bottomNavigationView.setSelectedItemId(R.id.track);

        bottomNavigationView.getMenu().getItem(4)
                .setIcon(R.drawable.ic_symptom)
                .setTitle("Symptom");


        btnTambah = findViewById(R.id.btnTambah);

        btnTambah.setOnClickListener(view -> {
            view.setSelected(true);
            startActivity(new Intent(SymptomActivity.this, AddSymptomActivity.class));
        });

        bottomNav();

    }

    private void bottomNav(){
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){
                case R.id.home:
                    Intent intentHome = new Intent(SymptomActivity.this, HomeActivity.class);
                    intentHome.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentHome);
                    break;
                case R.id.search:
                    Intent intentSearch = new Intent(SymptomActivity.this, SearchActivity.class);
                    intentSearch.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentSearch);
                    break;
                case R.id.drug:
                    Intent intentDrug = new Intent(SymptomActivity.this, DrugActivity.class);
                    intentDrug.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentDrug);
                    break;
                case R.id.reminder:
                    Intent intentReminder = new Intent(SymptomActivity.this, PengingatObatActivity.class);
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
            Intent intent = new Intent(SymptomActivity.this, SymptomActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        btnDrink.setOnClickListener(view -> {
            Intent intent = new Intent(SymptomActivity.this, DrinkActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        btnSleep.setOnClickListener(view -> {
            Intent intent = new Intent(SymptomActivity.this, SleepActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

    }


}