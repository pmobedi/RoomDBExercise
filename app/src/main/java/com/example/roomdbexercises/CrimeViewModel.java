package com.example.roomdbexercises;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class CrimeViewModel extends AndroidViewModel {
    private CrimeRepository repository;
    private LiveData<List<Crime>> allCrimes;

    public CrimeViewModel(Application application) {
        super(application);
        repository = new CrimeRepository(application);
        allCrimes = repository.getAllCrimes();
    }

    public LiveData<List<Crime>> getAllCrimes() {
        return allCrimes;
    }

    public void insertCrime(Crime crime) {
        repository.insertCrime(crime);
    }

    public void updateCrime(Crime crime) {
        repository.updateCrime(crime);
    }
}