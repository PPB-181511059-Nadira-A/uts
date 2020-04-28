package com.example.poin1;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import androidx.annotation.VisibleForTesting;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * The Room database.
 */
@Database(entities = {Cheese.class}, version = 1, exportSchema = false)
public abstract class SampleDatabase extends RoomDatabase {

    public abstract CheeseDao cheeseDao();

    private static volatile SampleDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                CheeseDao dao = INSTANCE.cheeseDao();
                dao.deleteAll();

                Cheese cheese = new Cheese("Abbaye de Belloc");
                dao.insert(cheese);
                cheese = new Cheese("Abbaye du Mont des Cats");
                dao.insert(cheese);
                cheese = new Cheese("Abertam");
                dao.insert(cheese);

            });
        }
    };
    static SampleDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SampleDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SampleDatabase.class, "cheese_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

