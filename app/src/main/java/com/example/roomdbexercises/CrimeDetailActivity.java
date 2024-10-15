package com.example.roomdbexercises;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_detail);

        crimeTitleEditText = findViewById(R.id.crime_title);
        crimeDateButton = findViewById(R.id.crime_date);
        crimeSolvedCheckBox = findViewById(R.id.crime_solved);
        saveCrimeButton = findViewById(R.id.save_crime_button);

        crimeViewModel = new ViewModelProvider(this).get(CrimeViewModel.class);

        crimeDateButton.setOnClickListener(v -> {
            // انتخاب تاریخ با استفاده از DatePickerDialog
            showDatePicker();
        });

        saveCrimeButton.setOnClickListener(v -> {
            // ذخیره‌سازی جزئیات جرم در پایگاه داده
            saveCrime();
        });
    }
    // این متد منو را ایجاد می‌کند
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.crime_menu, menu);
        return true;
    }

    // این متد مدیریت کلیک روی آیتم‌های منو را انجام می‌دهد

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_item_edit_crime) {
            editCrime();
            return true;
        } else if (id == R.id.menu_item_delete_crime) {
            deleteCrime();
            return true;
        }
        return super.onOptionsItemSelected(item);
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

        // ایجاد یک جرم جدید و پر کردن داده‌های آن
        currentCrime = new Crime(UUID.randomUUID(), title, date, isSolved);

        // ذخیره جرم در پایگاه داده از طریق ViewModel
        crimeViewModel.insertCrime(currentCrime);

        // نمایش پیام موفقیت‌آمیز
        Toast.makeText(this, "جرم ذخیره شد", Toast.LENGTH_SHORT).show();

        // بستن اکتیویتی پس از ذخیره
        finish();
    }

    private void editCrime() {
        // پیاده‌سازی ویرایش جرم
        Toast.makeText(this, "ویرایش جرم", Toast.LENGTH_SHORT).show();
        // می‌توانید منطق ویرایش را در اینجا پیاده‌سازی کنید
    }

    private void deleteCrime() {
        // پیاده‌سازی حذف جرم
        Toast.makeText(this, "جرم حذف شد", Toast.LENGTH_SHORT).show();
        // می‌توانید منطق حذف را در اینجا پیاده‌سازی کنید
    }
}
