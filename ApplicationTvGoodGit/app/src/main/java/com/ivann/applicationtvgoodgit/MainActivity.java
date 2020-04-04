package com.ivann.applicationtvgoodgit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ivann.applicationtvgoodgit.movieDb.FilmJson;
import com.ivann.applicationtvgoodgit.movieDb.filmDbApi;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ivann.applicationtvgoodgit.SearchActivity.transforminFilmcleaned;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textViewFavoris = findViewById(R.id.textViewFavoris);
        textViewFavoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFavoris();
            }
        });

        final TextView textViewRecherche = findViewById(R.id.textViewRecherche);
        textViewRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSearch();
            }
        });

        final TextView textViewAVenir = findViewById(R.id.textViewAVenir);
        textViewAVenir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.themoviedb.org/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                // on générée l'interface => on dit donc :  tu me generes une implémentation de la class filmDbApi que l'on va stocker dans service
                filmDbApi service = retrofit.create(filmDbApi.class);
                Call<SearchWrapper> callJson = service.findFilmToCome("d0f80747d8ac43db918936f4a3d09e9c", "fr", 1, "FR");
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


                        Intent intent = new Intent(MainActivity.this, LaterActivity.class);
                        intent.putParcelableArrayListExtra("FilmList", (ArrayList<? extends Parcelable>) filmList);
                        startActivity(intent);
                    }
                });

                final TextView textViewNouveautés = findViewById(R.id.textViewNouveautés);
                textViewNouveautés.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://api.themoviedb.org/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        // on générée l'interface => on dit donc :  tu me generes une implémentation de la class filmDbApi que l'on va stocker dans service
                        filmDbApi service = retrofit.create(filmDbApi.class);


                        // utilisation d'un des services => ici test avec trois services
                        Call<SearchWrapper> callJson = service.searchNowPlaying("d0f80747d8ac43db918936f4a3d09e9c", "fr", 1, "FR");

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

                                Intent intent = new Intent(MainActivity.this, NewFilmsActivity.class);
                                intent.putParcelableArrayListExtra("FilmList", (ArrayList<? extends Parcelable>) filmList);
                                startActivity(intent);

                            }

                        });
                    }
                });

                final TextView textViewAPropos = findViewById(R.id.textViewAPropos);
                textViewAPropos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToAbout();
                    }
                });

            }

        });
    }

    private void goToFavoris() {
        final Intent intentFavoris = new Intent(this, FavorisActivity.class);
        startActivity(intentFavoris);
    }

    private void goToSearch() {
        final Intent intentSearch = new Intent(this, SearchActivity.class);
        startActivity(intentSearch);
    }

    private void goToNewFilms() {
        final Intent intentNewFilms = new Intent(this, NewFilmsActivity.class);
        startActivity(intentNewFilms);
    }

    private void goToAbout() {
        final Intent intentAbout = new Intent(this, AboutActivity.class);
        startActivity(intentAbout);
    }

    public List<Film> transforminFilmcleaned(List<FilmJson> filmJsonList) {

        List<Film> filmList = new ArrayList<Film>();

        for (int i = 0; i < filmJsonList.size(); i++) {
            FilmJson filmJsonToModify = filmJsonList.get(i);
            //String genreModify = filmToModify.Genre.get(0);
            Film film = new Film(filmJsonToModify.getIdFilm(), filmJsonToModify.getFilmImage(), filmJsonToModify.getTitre(), filmJsonToModify.getDateSortie(), "genreModify", filmJsonToModify.getResume(), filmJsonToModify.getPopularite(), false, false);
            filmList.add(film);
        }
        return filmList;
    }
}
