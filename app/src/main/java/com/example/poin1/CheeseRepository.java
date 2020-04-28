package com.example.poin1;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CheeseRepository {
    private CheeseDao mCheeseDao;
    private LiveData<List<Cheese>> mAllCheese;

    CheeseRepository(Application application) {
        SampleDatabase db = SampleDatabase.getDatabase(application);
        mCheeseDao = db.cheeseDao();
        mAllCheese = mCheeseDao.getAlphabetizedWords();
    }

    LiveData<List<Cheese>> getmAllCheese() {
        return mAllCheese;
    }

    void insert(final Cheese cheese) {
        SampleDatabase.databaseWriteExecutor.execute(() -> {
            mCheeseDao.insert(cheese);
        });
    }
}