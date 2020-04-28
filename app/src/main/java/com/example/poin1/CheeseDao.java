package com.example.poin1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import android.database.Cursor;

import java.util.List;


@Dao
public interface CheeseDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insert(Cheese cheese);

        @Query("DELETE FROM cheese_table")
        void deleteAll();

        @Query("SELECT * from cheese_table ORDER BY cheese ASC")
        LiveData<List<Cheese>> getAlphabetizedWords();
    }
