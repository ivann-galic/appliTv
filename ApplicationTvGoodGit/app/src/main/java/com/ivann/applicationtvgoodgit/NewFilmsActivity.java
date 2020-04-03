package com.ivann.applicationtvgoodgit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.ivann.applicationtvgoodgit.movieDb.FilmJson;
import com.ivann.applicationtvgoodgit.movieDb.filmDbApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ivann.applicationtvgoodgit.SearchActivity.transforminFilmcleaned;

public class NewFilmsActivity extends AppCompatActivity {


    private List<Film> filmList;
    private FilmAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_films);


                Intent srcIntent = getIntent();
                filmList = srcIntent.getParcelableArrayListExtra("FilmList");
                setContentView(R.layout.activity_new_films);
                adapter = new FilmAdapter(filmList);

       /* for (int i=0; i<filmList.size(); i++){
            filmList.get(i);
        }*/
                RecyclerView recyclerView = findViewById(R.id.recyclerViewAffiche);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(adapter);
            }
}
