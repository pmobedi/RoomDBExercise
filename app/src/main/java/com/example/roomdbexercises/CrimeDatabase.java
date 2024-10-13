package com.example.roomdbexercises;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Crime.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class CrimeDatabase extends RoomDatabase {
    private static CrimeDatabase instance;

    public abstract CrimeDao crimeDao();

    public static synchronized CrimeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            CrimeDatabase.class, "crime_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
