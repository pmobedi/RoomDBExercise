package com.example.roomdbexercises;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class CrimeDetailActivity extends AppCompatActivity {

    private EditText crimeTitleEditText;
    private Button crimeDateButton;
    private CheckBox crimeSolvedCheckBox;
    private Button saveCrimeButton;
    private CrimeViewModel crimeViewModel;

    private Crime currentCrime;
    private String selectedDate;
    private UUID crimeId; // برای نگهداری CRIME_ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_detail);

        crimeTitleEditText = findViewById(R.id.crime_title);
        crimeDateButton = findViewById(R.id.crime_date);
        crimeSolvedCheckBox = findViewById(R.id.crime_solved);
        saveCrimeButton = findViewById(R.id.save_crime_button);

        crimeViewModel = new ViewModelProvider(this).get(CrimeViewModel.class);

        // دریافت CRIME_ID از Intent
        String crimeIdString = getIntent().getStringExtra("CRIME_ID");
        if (crimeIdString != null) {
            crimeId = UUID.fromString(crimeIdString);
            loadCrimeDetails(crimeId); // بارگذاری جزئیات جرم
        }

        crimeDateButton.setOnClickListener(v -> {
            // انتخاب تاریخ با استفاده از DatePickerDialog
            showDatePicker();
        });

        saveCrimeButton.setOnClickListener(v -> {
            // ذخیره‌سازی جزئیات جرم در پایگاه داده
            saveCrime();
        });
    }

    private void loadCrimeDetails(UUID crimeId) {
        // بارگذاری جزئیات جرم با استفاده از ViewModel
        crimeViewModel.getCrime(crimeId).observe(this, crime -> {
            if (crime != null) {
                // به‌روزرسانی UI با جزئیات جرم
                crimeTitleEditText.setText(crime.getTitle());
                crimeSolvedCheckBox.setChecked(crime.isSolved());

                // تبدیل تاریخ به فرمت دلخواه و نمایش در دکمه
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                selectedDate = dateFormat.format(crime.getDate());
                crimeDateButton.setText(selectedDate);
            }
        });
    }

    private void showDatePicker() {
        // گرفتن تاریخ فعلی از Calendar
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // ایجاد و نمایش DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // وقتی کاربر یک تاریخ انتخاب می‌کند
                    selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    crimeDateButton.setText(selectedDate); // نمایش تاریخ انتخاب شده در دکمه
                },
                year, month, day
        );

        // نمایش DatePickerDialog
        datePickerDialog.show();
    }

    private void saveCrime() {
        // گرفتن داده‌ها از فیلدهای ورودی
        String title = crimeTitleEditText.getText().toString().trim();
        boolean isSolved = crimeSolvedCheckBox.isChecked();

        if (title.isEmpty() || selectedDate == null) {
            Toast.makeText(this, "لطفاً همه فیلدها را پر کنید", Toast.LENGTH_SHORT).show();
            return;
        }

        // تبدیل رشته‌ی تاریخ به Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date = null;
        try {
            date = dateFormat.parse(selectedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(this, "خطا در فرمت تاریخ", Toast.LENGTH_SHORT).show();
            return;
        }

        // ایجاد یا به‌روزرسانی جرم
        if (crimeId != null) {
            currentCrime = new Crime(crimeId, title, date, isSolved);
            crimeViewModel.updateCrime(currentCrime); // به‌روزرسانی جرم در پایگاه داده
            Toast.makeText(this, "جرم به‌روزرسانی شد", Toast.LENGTH_SHORT).show();
        } else {
            currentCrime = new Crime(UUID.randomUUID(), title, date, isSolved);
            crimeViewModel.insertCrime(currentCrime); // ذخیره جرم جدید در پایگاه داده
            Toast.makeText(this, "جرم ذخیره شد", Toast.LENGTH_SHORT).show();
        }

        // بستن اکتیویتی پس از ذخیره
        finish();
    }
}
