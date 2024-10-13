package com.example.roomdbexercises;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.UUID;
import java.util.Date;

@Entity(tableName = "crime_table")
public class Crime {
    @PrimaryKey
    @NonNull
    private UUID id;
    private String title;
    private Date date;
    private boolean isSolved;

    public Crime(UUID id, String title, Date date, boolean isSolved) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.isSolved = isSolved;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public boolean isSolved() { return isSolved; }
    public void setSolved(boolean solved) { isSolved = solved; }
}
