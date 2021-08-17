package com.diary.project.dashboard;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diary.project.databinding.AdapterDiaryBinding;
import com.diary.project.databinding.EditDiaryBinding;
import com.diary.project.models.ModelDiary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class  AdapterDiary extends RecyclerView.Adapter<AdapterDiary.AdapterHolder> {

    Context context;
    ArrayList<ModelDiary> arrayList;
    AdapterDiaryBinding binding;
    String keyId = "";
    String date;
    UpdateDiary diary;

    public AdapterDiary(Context context, ArrayList<ModelDiary> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        diary = (UpdateDiary) context;
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
            ModelDiary modelDiary1 = arrayList.get(position);
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            EditDiaryBinding editbinding = EditDiaryBinding.inflate(layoutInflater);

            editDiaryku(modelDiary1, editbinding);
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

    private void editDiaryku(ModelDiary md,EditDiaryBinding editDiaryBinding){
        Dialog dialog = new Dialog(context);
        View view = editDiaryBinding.getRoot();
        dialog.setContentView(view);
        dialog.show();

        editDiaryBinding.title.setText(md.getTitle());
        editDiaryBinding.description.setText(md.getDescription());

        keyId = md.getUserId();

        editDiaryBinding.editbt.setOnClickListener(v ->{
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
            Calendar calendar = Calendar.getInstance();

            date = sdf.format(calendar.getTime());

            String title = editDiaryBinding.title.getText().toString();
            String description = editDiaryBinding.description.getText().toString();

            ModelDiary mdEdit = new ModelDiary(title, description, date, keyId);
            diary.editDiary(mdEdit);

            new Handler().post(new Runnable() {
                @Override
                public void run() {

                    Log.d("Edit: ", "Success");

                }
            });
        });
    }

    private  void deleteDiaryku(){

    }
}
