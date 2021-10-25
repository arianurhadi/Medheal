package com.one.medheal;

import static android.view.Gravity.START;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        AppCompatButton btnSidebar = findViewById(R.id.btnSidebar);
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navView = findViewById(R.id.navView);

        btnSidebar.setOnClickListener(view -> {
            drawerLayout.openDrawer(START);
        });

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawer(drawerLayout);
                return true;
            }
        });

    }
}