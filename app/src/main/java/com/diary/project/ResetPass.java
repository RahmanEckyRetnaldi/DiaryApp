package com.diary.project;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.diary.project.databinding.ActivityForgetBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPass extends AppCompatActivity {

    ActivityForgetBinding binding;
    FirebaseAuth auth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth  = FirebaseAuth.getInstance();

        binding.reset.setOnClickListener(v ->{
            String email = binding.email.getText().toString();
        });
    }

    private void resetPass(String email){
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(ResetPass.this, "Please Check your email", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ResetPass.this, "Email not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
