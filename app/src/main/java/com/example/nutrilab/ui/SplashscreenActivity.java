package com.example.nutrilab.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nutrilab.R;
import com.example.nutrilab.pref.SharedPrefManager;

public class SplashscreenActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String userId = SharedPrefManager.getInstance(SplashscreenActivity.this).getUserId();
                Intent intent;
                if (userId != null && !userId.isEmpty()) {
                    intent = new Intent(SplashscreenActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(SplashscreenActivity.this, LandingActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, SPLASH_DELAY);

    }
}