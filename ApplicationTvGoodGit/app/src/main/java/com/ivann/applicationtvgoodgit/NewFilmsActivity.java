package com.ivann.applicationtvgoodgit;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_films);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // on générée l'interface => on dit donc :  tu me generes une implémentation de la class filmDbApi que l'on va stocker dans service
        filmDbApi service = retrofit.create(filmDbApi.class);


        // utilisation d'un des services => ici test avec trois services
        Call<SearchWrapper> callJson = service.searchNowPlaying("d0f80747d8ac43db918936f4a3d09e9c", "fr",1,"FR");

        callJson.enqueue(new Callback<SearchWrapper>() {

            @Override
            public void onFailure(Call<SearchWrapper> call, Throwable t) {
                Log.e("MainActivity", "onFailure = " + t.getMessage());
            }

            @Override
            public void onResponse(Call<SearchWrapper> call, Response<SearchWrapper> response) {
                assert response.body() != null;
                List<FilmJson> filmJsonList = response.body().results;
                List<Film> filmList = transforminFilmcleaned(filmJsonList);

                Intent intent = new Intent(NewFilmsActivity.this, VoirPlusTardActivity.class);
                intent.putParcelableArrayListExtra("FilmList", (ArrayList<? extends Parcelable>) filmList);
                startActivity(intent);

            }
        });
    }



    }
}
