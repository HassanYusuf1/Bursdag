package com.example.s374221_mappe2;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.s374221_mappe2.daos.VennerDao;
import com.example.s374221_mappe2.enteties.Venner;

// Angi entiteter og databaseversjon
@Database(entities = {Venner.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract VennerDao vennerDao();

    private static volatile AppDatabase INSTANCE;

    // Singleton-mønster for databaseopprettelse
    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "App_Database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
