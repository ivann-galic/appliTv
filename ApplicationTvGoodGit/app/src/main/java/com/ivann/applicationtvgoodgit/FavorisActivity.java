package com.ivann.applicationtvgoodgit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.ivann.applicationtvgoodgit.database.FilmDao;

import java.util.List;

public class FavorisActivity extends AppCompatActivity {

    private FilmAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);

        FilmDao dao = App.db.filmDao();

        List<Film> favorisFilms = dao.getAll();
        Log.i("favorisActivity", "size" + favorisFilms.size());




        setContentView(R.layout.activity_favoris);
        adapter = new FilmAdapter(favorisFilms);

       /* for (int i=0; i<filmList.size(); i++){
            filmList.get(i);
        }*/
        RecyclerView recyclerView = findViewById(R.id.recyclerViewFavoris);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }

}
