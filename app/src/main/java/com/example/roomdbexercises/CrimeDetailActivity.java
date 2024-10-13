package com.example.roomdbexercises;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CrimeDetailActivity extends AppCompatActivity {

    private EditText crimeTitleEditText;
    private Button crimeDateButton;
    private CheckBox crimeSolvedCheckBox;
    private Button saveCrimeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_detail);

        crimeTitleEditText = findViewById(R.id.crime_title);
        crimeDateButton = findViewById(R.id.crime_date);
        crimeSolvedCheckBox = findViewById(R.id.crime_solved);
        saveCrimeButton = findViewById(R.id.save_crime_button);

        crimeDateButton.setOnClickListener(v -> {
            // انتخاب تاریخ (می‌توانید از DatePicker استفاده کنید)
            showDatePicker();
        });

        saveCrimeButton.setOnClickListener(v -> {
            // ذخیره‌سازی جزئیات جرم (در اینجا باید داده‌ها در پایگاه داده ذخیره شوند)
            saveCrime();
        });
    }

    private void showDatePicker() {
        // انتخاب تاریخ با استفاده از DatePickerDialog
    }

    private void saveCrime() {
        // ذخیره‌سازی جرم در پایگاه داده
    }
}
