
package com.ivann.applicationtvgoodgit;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListFilmActivity extends AppCompatActivity {

    private List<Film> filmList;
    private FilmAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_film);

        Intent srcintent = getIntent();
        this.filmList = srcintent.getParcelableArrayListExtra("FilmList");
        this.adapter = new FilmAdapter(filmList);

        for (int i=0; i<filmList.size(); i++){
            filmList.get(i);
        }
        RecyclerView recyclerView = findViewById(R.id.recyclerViewFilm);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);




    }


}

