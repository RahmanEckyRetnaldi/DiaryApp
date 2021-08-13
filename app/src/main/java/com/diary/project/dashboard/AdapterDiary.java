package com.diary.project.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diary.project.databinding.AdapterDiaryBinding;
import com.diary.project.models.ModelDiary;

import java.util.ArrayList;

public class    AdapterDiary extends RecyclerView.Adapter<AdapterDiary.AdapterHolder> {

    Context context;
    ArrayList<ModelDiary> arrayList;
    AdapterDiaryBinding binding;
    String keyId = "";
    String date;

    public AdapterDiary(Context context, ArrayList<ModelDiary> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public AdapterDiary.AdapterHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new AdapterHolder(AdapterDiaryBinding.inflate(layoutInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull  AdapterDiary.AdapterHolder holder, int position) {
        ModelDiary modelDiary = arrayList.get(position);
        String title = modelDiary.getTitle();
        String description = modelDiary.getDescription();
        String date = modelDiary.getDate();

        holder.binding.tvTitle.setText(title);
        holder.binding.tvDescription.setText(description);
        holder.binding.tvDate.setText(date);

        holder.binding.ivClose.setOnClickListener(v ->{

        });

        holder.binding.ivEdit.setOnClickListener(v -> {

        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {

        AdapterDiaryBinding binding;
        public AdapterHolder(@NonNull AdapterDiaryBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
