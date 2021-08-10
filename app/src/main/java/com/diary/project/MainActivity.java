package com.diary.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.diary.project.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(MainActivity.this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(MainActivity.this);
        binding.signup.setOnClickListener(v->{
            String name = binding.name.getText().toString();
            String email = binding.email.getText().toString();
            String pass = binding.email.getText().toString();
            storeDataToFirebase(name, email, pass);
        });
    }

    private void storeDataToFirebase(String name, String email, String password){

        dialog.setMessage("Please Wait . . .");
        dialog.show();

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                dialog.dismiss();
                if(task.isSuccessful()){
                    Log.d("Success","Data Terkirim");
                }else{
                    Log.d("Failed", "Data Gagal Terkirim");
                }

            }
        });
    }

}