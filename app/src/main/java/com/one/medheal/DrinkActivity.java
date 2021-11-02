package com.one.medheal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.one.medheal.drug.DrugActivity;
import com.one.medheal.drugreminder.PengingatObatActivity;
import com.one.medheal.sleep.SleepActivity;
import com.one.medheal.symptom.SymptomActivity;

public class DrinkActivity extends AppCompatActivity {

    boolean visible = true;

    private BottomNavigationView bottomNavigationView;
    private LinearLayout bottomSubMenu;
    private LinearLayout btnSymptom;
    private LinearLayout btnDrink;
    private LinearLayout btnSleep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        bottomNavigationView = findViewById(R.id.bottomNavBar);
        bottomSubMenu = findViewById(R.id.bottomSubmenu);
        btnSymptom = findViewById(R.id.btnSymptom);
        btnDrink = findViewById(R.id.btnDrink);
        btnSleep = findViewById(R.id.btnSleep);

        bottomNavigationView.setSelectedItemId(R.id.track);

        bottomNavigationView.getMenu().getItem(4)
                .setIcon(R.drawable.ic_drink)
                .setTitle("Drink");

        bottomNav();

    }

    private void bottomNav(){
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){
                case R.id.home:
                    Intent intentHome = new Intent(DrinkActivity.this, HomeActivity.class);
                    intentHome.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentHome);
                    break;
                case R.id.search:
                    Intent intentSearch = new Intent(DrinkActivity.this, SearchActivity.class);
                    intentSearch.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentSearch);
                    break;
                case R.id.drug:
                    Intent intentDrug = new Intent(DrinkActivity.this, DrugActivity.class);
                    intentDrug.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentDrug);
                    break;
                case R.id.reminder:
                    Intent intentReminder = new Intent(DrinkActivity.this, PengingatObatActivity.class);
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
            Intent intent = new Intent(DrinkActivity.this, SymptomActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        btnDrink.setOnClickListener(view -> {
            Intent intent = new Intent(DrinkActivity.this, DrinkActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        btnSleep.setOnClickListener(view -> {
            Intent intent = new Intent(DrinkActivity.this, SleepActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

    }

}