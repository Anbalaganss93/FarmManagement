package com.farmmanagement.roomDP;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {
        UserDetail.class,
        PondDetails.class
},
        version = 1,exportSchema = false)
public abstract class pondRoomDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    private static pondRoomDatabase INSTANCE;

    static pondRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (pondRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            pondRoomDatabase.class, "pond_database")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
