package com.diary.project;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.diary.project.databinding.ActivityDasboardBinding;

public class Dasboard extends AppCompatActivity {

    ActivityDasboardBinding binding;

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityDasboardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}
