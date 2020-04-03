package com.ivann.applicationtvgoodgit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.ivann.applicationtvgoodgit.database.FilmDao;

import java.util.List;

public class FavorisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);

        FilmDao dao = App.db.filmDao();

        Film f = new Film();
        f.titre = " vas te faire foutre romain";
        //dao.insertFilm(f);

        List<Film> films = dao.getAll();
        Log.i("favorisActivity", "size" + films.size());

    }
}
