package com.ivann.applicationtvgoodgit;
import android.app.Application;
import androidx.room.Room;
import com.ivann.applicationtvgoodgit.database.AppDatabase;

public class App extends Application {

    public static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "CoronowTv.db")
                .allowMainThreadQueries()
                .build();
    }
}
