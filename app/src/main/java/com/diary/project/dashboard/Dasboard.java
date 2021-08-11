package com.diary.project.dashboard;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.diary.project.databinding.ActivityDasboardBinding;
import com.diary.project.models.ModelDiary;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Dasboard extends AppCompatActivity {

    ActivityDasboardBinding binding;

    ArrayList<ModelDiary> diaries;
    DatabaseReference reference;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    LinearLayoutManager linearLayoutManager;
    AdapterDiary adapterDiary;
    ModelDiary modalDiary;
    ProgressDialog dialog;
    String unique;

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityDasboardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        preferences = getSharedPreferences("Diaryku", MODE_PRIVATE);
        unique = preferences.getString("unique","");
        reference = FirebaseDatabase.getInstance().getReference("UserDiary").child(unique);
        diaries = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this);
        dialog = new ProgressDialog(this);


        binding.rvDashboard.setLayoutManager(linearLayoutManager);

        showDiary();
    }
     private void showDiary(){
        dialog.setMessage("Please wait . . .");
        dialog.show();
        diaries.clear();

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds : snapshot.getChildren()){
                    modalDiary = ds.getValue(ModelDiary.class);
                    diaries.add(modalDiary);

                    adapterDiary = new AdapterDiary(Dasboard.this, diaries);
                    binding.rvDashboard.setAdapter(adapterDiary);
                    binding.noitem.setVisibility(View.GONE);
                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                binding.noitem.setVisibility(View.VISIBLE);
            }
        });
     }
}
