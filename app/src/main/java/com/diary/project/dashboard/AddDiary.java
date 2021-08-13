package com.diary.project.dashboard;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.diary.project.databinding.AddDiaryBinding;
import com.diary.project.models.ModelDiary;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddDiary extends AppCompatActivity {
    AddDiaryBinding binding;
    SharedPreferences preferences;
    String uniqueId;
    String keyUnique;
    DatabaseReference reference;
    ProgressDialog dialog;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = AddDiaryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        preferences = getSharedPreferences("Diaryku",MODE_PRIVATE);
        uniqueId = preferences.getString("unique","");
        reference = FirebaseDatabase.getInstance().getReference("DataUser").child(uniqueId);
        dialog = new ProgressDialog(this);

        binding.sumbit.setOnClickListener(v ->{
            String title = binding.title.getText().toString();
            String description = binding.description.getText().toString();

           SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMM yyyy");
            Calendar calendar = Calendar.getInstance();
            String fixdate = sdf.format(calendar.getTime());

            dialog.setMessage("Please Wait ...");
            dialog.show();

            keyUnique = reference.push().getKey();
            ModelDiary modelDiary = new ModelDiary(title, description, fixdate, keyUnique);
            reference.child(keyUnique).setValue(modelDiary).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(AddDiary.this, "Success Sumbit", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        finish();
                    }else{
                        Toast.makeText(AddDiary.this, "Failed Sumbit", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        });
    }
}
