package com.diary.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.diary.project.databinding.ActivitySiginBinding;

public class SignIn extends AppCompatActivity {

    ActivitySiginBinding binding;

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySiginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.signin.setOnClickListener(v->{
            storeLoginUser("", "");
        });

        binding.signup.setOnClickListener(v->{
            startActivity(new Intent(SignIn.this, SignUp.class));
        });

    }

    private void storeLoginUser(String email, String Password){

    }
}
