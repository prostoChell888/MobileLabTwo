package com.example.lab2.db;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.lab2.db.dao.AppDao;
import com.example.lab2.db.model.Episode;
import com.example.lab2.db.model.Personage;

@Database(entities = {Personage.class, Episode.class}, version = 2)
public abstract class DataBase extends RoomDatabase {
    private static DataBase instance;

    public abstract AppDao PersonageDao();

    public static synchronized DataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            DataBase.class, "data_base")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static final RoomDatabase.Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };


}

