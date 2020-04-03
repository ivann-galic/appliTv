
package com.ivann.applicationtvgoodgit;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class ListFilmActivity extends AppCompatActivity {

    private List<Film> film;
    private FilmAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent srcIntent = getIntent();
        film = srcIntent.getParcelableArrayListExtra("FilmList");


        setContentView(R.layout.activity_list_film);
        adapter = new FilmAdapter(film);

       /* for (int i=0; i<filmList.size(); i++){
            filmList.get(i);
        }*/
        RecyclerView recyclerView = findViewById(R.id.recyclerViewFilm);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

}

