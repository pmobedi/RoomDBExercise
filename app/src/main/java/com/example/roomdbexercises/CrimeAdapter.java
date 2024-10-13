package com.example.roomdbexercises;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.CrimeHolder> {

    private List<Crime> crimes;

    public CrimeAdapter(List<Crime> crimes) {
        this.crimes = crimes;
    }

    @NonNull
    @Override
    public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CrimeHolder(inflater.inflate(R.layout.list_item_crime, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
        holder.bind(crimes.get(position));
    }

    @Override
    public int getItemCount() {
        return crimes.size();
    }

    class CrimeHolder extends RecyclerView.ViewHolder {

        private TextView crimeTitleTextView;
        private TextView crimeDateTextView;
        private CheckBox crimeSolvedCheckBox;

        public CrimeHolder(@NonNull View itemView) {
            super(itemView);
            crimeTitleTextView = itemView.findViewById(R.id.crime_title);
            crimeDateTextView = itemView.findViewById(R.id.crime_date);
            crimeSolvedCheckBox = itemView.findViewById(R.id.crime_solved);
        }

        public void bind(Crime crime) {
            crimeTitleTextView.setText(crime.getTitle());
            crimeDateTextView.setText(crime.getDate().toString());
            crimeSolvedCheckBox.setChecked(crime.isSolved());
        }
    }
}
