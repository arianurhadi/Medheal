package com.one.medheal.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {User.class, Obat.class}, version = 2)
public abstract class Database extends RoomDatabase {

    private static final String DB_NAME = "medheal_db";
    private static Database instance;

    public static synchronized Database getInstance (Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), Database.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract UserDao userDao();
    public abstract ObatDao obatDao();

}
