package com.example.roomdbexercises;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.CrimeHolder> {

    private List<Crime> crimes;
    private Context context;
    private CrimeRepository crimeRepository;
    public CrimeAdapter(List<Crime> crimeList, Context context,Application application) {
        this.crimes= crimeList;
        this.context = context;
        this.crimeRepository = new CrimeRepository(application);
    }

    @NonNull
    @Override
    public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_crime, parent, false);
        Log.d("CrimeAdapter", "Creating new CrimeHolder");
        return new CrimeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
        // دسترسی به جرم مرتبط با موقعیت
        Crime crime = crimes.get(position); // تعریف متغیر crime در این خط

        holder.bind(crime); // تنظیم اطلاعات در ویو
        // تنظیم دکمه ویرایش
        holder.editButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, CrimeDetailActivity.class);
            intent.putExtra("CRIME_ID", crime.getId().toString());
            context.startActivity(intent);
        });

        // تنظیم دکمه حذف
        holder.deleteButton.setOnClickListener(v -> {
            // گرفتن ID از crime و ارسال آن به متد deleteCrime
            crimeRepository.deleteCrime(crime.getId()); // به جای ارسال جرم، ID آن ارسال می‌شود
            Toast.makeText(context, "جرم حذف شد", Toast.LENGTH_SHORT).show();
            notifyItemRemoved(position);
            crimes.remove(position); // حذف آیتم از لیست
        });


    }

    @Override
    public int getItemCount() {
        return crimes.size();
    }

    class CrimeHolder extends RecyclerView.ViewHolder {

        private TextView crimeTitleTextView;
        private TextView crimeDateTextView;
        private CheckBox crimeSolvedCheckBox;
        Button editButton;
        Button deleteButton;

        public CrimeHolder(@NonNull View itemView) {
            super(itemView);
            crimeTitleTextView = itemView.findViewById(R.id.crime_title);
            crimeDateTextView = itemView.findViewById(R.id.crime_date);
            crimeSolvedCheckBox = itemView.findViewById(R.id.crime_solved);
            editButton = itemView.findViewById(R.id.edit_button);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }

        public void bind(Crime crime) {
            crimeTitleTextView.setText(crime.getTitle());
            crimeDateTextView.setText(crime.getDate().toString());
            if (crimeSolvedCheckBox != null) {
                crimeSolvedCheckBox.setChecked(crime.isSolved());
            } else {
                Log.e("CrimeHolder", "crimeSolvedCheckBox is null");
            }
        }
    }
}
