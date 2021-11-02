package com.one.medheal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.one.medheal.adapter.ObatRvAdapter;
import com.one.medheal.database.Database;
import com.one.medheal.database.Obat;

import java.util.ArrayList;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private LinearLayout bottomSubMenu;
    private LinearLayout btnSymptom;
    private LinearLayout btnDrink;
    private LinearLayout btnSleep;

    private EditText search;
    private RecyclerView rvObat;
    private ObatRvAdapter adapter;
    private Database db;

    private ArrayList<Obat> listObat;

    boolean visible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_search);

        bottomNavigationView = findViewById(R.id.bottomNavBar);
        bottomSubMenu = findViewById(R.id.bottomSubmenu);
        btnSymptom = findViewById(R.id.btnSymptom);
        btnDrink = findViewById(R.id.btnDrink);
        btnSleep = findViewById(R.id.btnSleep);

        search = findViewById(R.id.searchView);
        rvObat = findViewById(R.id.rvObat);

        listObat = new ArrayList<Obat>();

        db = Database.getInstance(this);

        bottomNavigationView.setSelectedItemId(R.id.search);

        getData();

        bottomNav();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                filterList(charSequence.toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.e("make", editable.toString());
                filterList(editable.toString());
            }
        });

        showRv();
    }

    private void getData(){
        listObat = (ArrayList<Obat>) db.obatDao().getAllObat();
    }

    private void showRv(){
        adapter = new ObatRvAdapter(listObat, this, false);
        rvObat.setLayoutManager(new GridLayoutManager(this, 2));
        rvObat.setAdapter(adapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void filterList(String text){

        ArrayList<Obat> listFilter = new ArrayList<Obat>();

        for (Obat data : db.obatDao().getAllObat()){
            if (data.getNamaObat().toLowerCase(Locale.ROOT).contains(text)){
                listFilter.add(data);
            }
        }

        Log.e("test", listFilter.toString());
        adapter.setList(listFilter);
        adapter.notifyDataSetChanged();
    }

    private void bottomNav(){
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch(item.getItemId()){
                case R.id.home:
                    Intent intentHome = new Intent(SearchActivity.this, HomeActivity.class);
                    intentHome.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentHome);
                    break;
                case R.id.search:
                    Intent intentSearch = new Intent(SearchActivity.this, SearchActivity.class);
                    intentSearch.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentSearch);
                    break;
                case R.id.drug:
                    Intent intentDrug = new Intent(SearchActivity.this, DrugActivity.class);
                    intentDrug.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intentDrug);
                    break;
                case R.id.reminder:
                    Intent intentReminder = new Intent(SearchActivity.this, PengingatObatActivity.class);
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
            Intent intent = new Intent(SearchActivity.this, SymptomActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        btnDrink.setOnClickListener(view -> {
            Intent intent = new Intent(SearchActivity.this, DrinkActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

        btnSleep.setOnClickListener(view -> {
            Intent intent = new Intent(SearchActivity.this, SleepActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

    }

}