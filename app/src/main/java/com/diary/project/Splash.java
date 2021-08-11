package com.diary.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.diary.project.databinding.ActivitySplashBinding;

public class Splash extends AppCompatActivity {

    ActivitySplashBinding binding;
    SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);
        preferences = getSharedPreferences("Diaryku", MODE_PRIVATE);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if(preferences.getBoolean("AutoLogin", false)){
                    startActivity(new Intent(Splash.this, Dasboard.class));
                }else{
                    startActivity(new Intent(Splash.this, SignIn.class));
                }
                finish();
            }
        });
    }
}
