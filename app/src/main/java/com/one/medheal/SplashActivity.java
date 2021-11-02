package com.one.medheal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.one.medheal.preferences.Preferences;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();

        if (Preferences.getStatusFirstRun(SplashActivity.this)){
            Preferences.setFirstRun(SplashActivity.this, false);
            handler.postDelayed(() -> {
                Intent intent = new Intent(SplashActivity.this, OnBoardingActivity.class);
                startActivity(intent);
                finish();
            }, 1000);
        } else {
            handler.postDelayed(() -> {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }, 1000);
        }

    }
}