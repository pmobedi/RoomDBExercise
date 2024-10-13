package com.example.roomdbexercises;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CrimeRepository {
    private CrimeDao crimeDao;
    private LiveData<List<Crime>> allCrimes;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public CrimeRepository(Application application) {
        CrimeDatabase db = CrimeDatabase.getInstance(application);
        crimeDao = db.crimeDao();
        allCrimes = crimeDao.getAllCrimes();
    }

    public LiveData<List<Crime>> getAllCrimes() {
        return allCrimes;
    }

    public void insertCrime(Crime crime) {
        executorService.execute(() -> crimeDao.insertCrime(crime));
    }

    public void updateCrime(Crime crime) {
        executorService.execute(() -> crimeDao.updateCrime(crime));
    }

    public Crime getCrimeById(UUID id) {
        return crimeDao.getCrimeById(id);
    }

    public void deleteCrime(UUID id) {
        executorService.execute(() -> crimeDao.deleteCrime(id));
    }
}
