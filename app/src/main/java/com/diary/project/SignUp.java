package com.diary.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.diary.project.databinding.ActivitySigupBinding;
import com.diary.project.models.UserDiary;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    ActivitySigupBinding binding;
    FirebaseAuth auth;
    ProgressDialog dialog;
    DatabaseReference reference;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(SignUp.this);
        binding = ActivitySigupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(SignUp.this);
        reference = FirebaseDatabase.getInstance().getReference("UserDiary");

        preferences = getSharedPreferences("Diaryku", MODE_PRIVATE);
        editor = preferences.edit();
        //button signup click
        binding.signup.setOnClickListener(v->{
            String name = binding.name.getText().toString();
            String email = binding.email.getText().toString();
            String pass = binding.email.getText().toString();
            storeDataToFirebase(name, email, pass);
        });

        //button signin click
        binding.signin.setOnClickListener(v->{
            startActivity(new Intent(SignUp.this, SignIn.class));
        });
    }

    private void storeDataToFirebase(String name, String email, String password){

        dialog.setMessage("Please Wait . . .");
        dialog.show();

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = auth.getCurrentUser();
                    String unique = user.getUid();
                    UserDiary diary = new UserDiary(name, email, "");
                    reference.child(unique).setValue(diary).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull  Task<Void> task) {
                            dialog.dismiss();
                            if(task.isSuccessful()){
                                editor.putString("unique", unique);
                                editor.commit();
                                Toast.makeText(SignUp.this, "Register Success", Toast.LENGTH_SHORT).show();
                                Log.d("Succes: ", task+" Success");
                                finish();
                            }
                        }
                    });
                }else{
                    Toast.makeText(SignUp.this, "Register Failed", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

}