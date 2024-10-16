package com.example.roomdbexercises;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import java.util.UUID;

@Dao
public interface CrimeDao {
    @Insert
    void insertCrime(Crime crime);

    @Update
    void updateCrime(Crime crime);

    @Query("SELECT * FROM crime_table")
    LiveData<List<Crime>> getAllCrimes();

    @Query("SELECT * FROM crime_table WHERE id = :id")
    Crime getCrimeById(UUID id);

    @Query("DELETE FROM crime_table WHERE id = :id")
    void deleteCrime(UUID id);
    @Query("SELECT * FROM crime_table WHERE id = :id")
    LiveData<Crime> getCrime(UUID id);

}
