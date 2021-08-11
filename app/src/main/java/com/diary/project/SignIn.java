package com.diary.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.diary.project.dashboard.Dasboard;
import com.diary.project.databinding.ActivitySiginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    ActivitySiginBinding binding;
    FirebaseAuth auth;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySiginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        dialog = new ProgressDialog(SignIn.this);
        auth = FirebaseAuth.getInstance();
        preferences = getSharedPreferences("Diaryku", MODE_PRIVATE);
        editor = preferences.edit();

        binding.signin.setOnClickListener(v->{
            String email = binding.email.getText().toString();
            String pass = binding.password.getText().toString();

            storeLoginUser(email, pass);
        });

        binding.signup.setOnClickListener(v->{
            startActivity(new Intent(SignIn.this, SignUp.class));
        });

    }

    private void storeLoginUser(String email, String password){
        dialog.setMessage("Please wait. . .");
        dialog.show();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    dialog.dismiss();
                    editor.putBoolean("AutoLogin", true);
                    editor.apply();
                    startActivity(new Intent(SignIn.this, Dasboard.class));
                    Toast.makeText(SignIn.this, "Login Success!",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    dialog.dismiss();
                    Toast.makeText(SignIn.this, "Login Failed!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
