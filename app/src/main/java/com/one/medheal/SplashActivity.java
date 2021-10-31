package com.one.medheal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "mySharedPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();

        SharedPreferences sharedPreferences = SplashActivity.this.getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean firstRun = sharedPreferences.getBoolean("firstRun", true);

        if (firstRun){
            editor.putBoolean("firstRun", false);
            editor.apply();
            handler.postDelayed((Runnable) () -> {
                Intent intent = new Intent(SplashActivity.this, OnBoardingActivity.class);
                startActivity(intent);
                finish();
            }, 1000);
        } else {
            handler.postDelayed((Runnable) () -> {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }, 1000);
        }

    }
}