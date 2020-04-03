package com.ivann.applicationtvgoodgit.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.ivann.applicationtvgoodgit.Film;

@Database(entities = {Film.class}, version = 1)
@TypeConverters(RoomUtils.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FilmDao filmDao();
}
