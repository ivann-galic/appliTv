package com.ivann.applicationtvgoodgit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class FavorisActivity extends AppCompatActivity {
    private List<Film> filmList;
    private FilmAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);
        Intent srcIntent = getIntent();
        filmList = srcIntent.getParcelableArrayListExtra("FilmList");


        setContentView(R.layout.activity_collections);
        adapter = new FilmAdapter(filmList);

       /* for (int i=0; i<filmList.size(); i++){
            filmList.get(i);
        }*/
        RecyclerView recyclerView = findViewById(R.id.recyclerViewFavoris);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

}
