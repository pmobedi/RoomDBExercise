package com.example.roomdbexercises;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CrimeListActivity extends AppCompatActivity {

    private RecyclerView crimeRecyclerView;
    private CrimeAdapter crimeAdapter;
    private CrimeRepository crimeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_list);

        crimeRecyclerView = findViewById(R.id.crime_recycler_view);
        crimeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        crimeRepository = new CrimeRepository(getApplication());
        crimeRepository.getAllCrimes().observe(this, crimes -> {
            crimeAdapter = new CrimeAdapter(crimes);
            crimeRecyclerView.setAdapter(crimeAdapter);
        });

        findViewById(R.id.add_crime_button).setOnClickListener(v -> {
            Intent intent = new Intent(CrimeListActivity.this, CrimeDetailActivity.class);
            startActivity(intent);
        });
    }
}
