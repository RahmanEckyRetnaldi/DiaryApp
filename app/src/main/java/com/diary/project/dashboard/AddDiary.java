package com.diary.project.dashboard;


import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.diary.project.databinding.AddDiaryBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddDiary extends AppCompatActivity {
    AddDiaryBinding binding;
    SharedPreferences preferences;
    String uniqueId;
    DatabaseReference reference;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = AddDiaryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        preferences = getSharedPreferences("Diaryku",MODE_PRIVATE);
        uniqueId = preferences.getString("unique","");
        reference = FirebaseDatabase.getInstance().getReference("UserDiary").child(uniqueId);

        binding.sumbit.setOnClickListener(v ->{
            String tittle = binding.title.getText().toString();
            String description = binding.description.getText().toString();

           SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMM yyyy");
            Calendar calendar = Calendar.getInstance();
            String fixdate = sdf.format(calendar.getTime());
        });
    }
}
