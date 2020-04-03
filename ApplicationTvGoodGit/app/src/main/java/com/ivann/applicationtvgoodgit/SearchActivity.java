package com.ivann.applicationtvgoodgit;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SearchActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        loadDataFromApi();
    }




    /*---------------------------------------------------GESTION API ---------------------------------------------------*/

    //---------------TRAITEMENT DE L'INPUT UTILISATEUR ------------------------------------/
    public String userChoice() {

        EditText inputUser = (EditText) findViewById(R.id.EditTextSearch);
        String userChoice = inputUser.getText().toString();
        return userChoice;
    }

    /**
     * requête Http à l'aide de Retrofit
     *
     * @param searchedText correspond à l'entrée de l'utilisateur
     */
    private void callData(String searchedText) {


        // construction d'un client apartir des données fournies par retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // on générée l'interface => on dit donc :  tu me generes une implémentation de la class filmDbApi que l'on va stocker dans service
        filmDbApi service = retrofit.create(filmDbApi.class);


        // utilisation d'un des services => ici test avec trois services
        Call<SearchWrapper> callJson = service.searchCategory("d0f80747d8ac43db918936f4a3d09e9c", "fr", "popularity.desc", 1);
        Call<SearchWrapper> callJson2 = service.searchMovies("d0f80747d8ac43db918936f4a3d09e9c", "fr", searchedText, 1);
        Call<SearchWrapper> callJson3 = service.searchMovies("d0f80747d8ac43db918936f4a3d09e9c", "fr", searchedText, 1);

        callJson3.enqueue(new Callback<SearchWrapper>() {

            @Override
            public void onFailure(Call<SearchWrapper> call, Throwable t) {
                Log.e("MainActivity", "onFailure = " + t.getMessage());
            }

            @Override
            public void onResponse(Call<SearchWrapper> call, Response<SearchWrapper> response) {
                assert response.body() != null;
                List<Film> filmList = response.body().results;

                Intent intent = new Intent(SearchActivity.this, ListFilmActivity.class);
                intent.putParcelableArrayListExtra("FilmList", (ArrayList<? extends Parcelable>) filmList);
                startActivity(intent);

                // CONSTITUTION DE LA LISTE DE FILMS


                Log.i("Mainactivity", "la liste de sfilms est = " + filmList.get(0).toString());


                // log d etest pour voir si cela a marché
                Log.i("MainActivity", "la list de sfilms " + filmList.get(0).toString());


                //----------------------------------------------- CHANGEMENT DE VIEW (start activity)--------------------------------------/
            }
        });

    }


    private void loadDataFromApi() {
        //String userChoice = userchoice();
        // A REMPACER PAR => String searchedFilm = search = research.Text


        // GESTION DES VUES
        final CardView cardViewGenres = (CardView) findViewById(R.id.CardViewGenres);
        final Button buttonFilter = findViewById(R.id.buttonFilter);
        final CardView cardViewFilter = findViewById(R.id.CardViewFilter);
        final ImageButton searchValidation = findViewById(R.id.imageButtonSearch);

        searchValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String searchedText = userChoice();
                callData(searchedText);

            }
        });
        // Lors d'un clic sur l'option "genre", une cardView (menu des filtres) prend la place pour afficher tous les genres.
        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewFilter.setVisibility(View.VISIBLE);

            }
        });


        // les boutons pour gérer les catégories
        final RadioGroup RadioGroupGenre1 = (RadioGroup) findViewById(R.id.RadioGroupGenre1);
        final RadioButton radioClickedGenre1 = findViewById(RadioGroupGenre1.getCheckedRadioButtonId());

        final RadioButton radioFilterGenre = (RadioButton) findViewById(R.id.radioButtonGenre);
        final RadioButton radioFilterDate = (RadioButton) findViewById(R.id.radioButtonDate);

        final RadioGroup RadioGroupGenre2 = (RadioGroup) findViewById(R.id.RadioGroupGenre2);
        final RadioButton radioClickedGenre2 = (RadioButton) findViewById(RadioGroupGenre2.getCheckedRadioButtonId());
        final RadioGroup RadioGroupFilter = (RadioGroup) findViewById(R.id.RadioGroupFilter);

// Tri par date du plus récent au plus vieux précision pour la date? possibilité de faire l'inverse du plus vieux au plus récent?
        radioFilterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.themoviedb.org/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                // on générée l'interface => on dit donc :  tu me generes une implémentation de la class filmDbApi que l'on va stocker dans service
                filmDbApi service = retrofit.create(filmDbApi.class);


                // utilisation d'un des services => ici test avec trois services
                Call<SearchWrapper> callJson = service.searchDate("d0f80747d8ac43db918936f4a3d09e9c", "fr", "release_date.desc", 1, 1900);

                callJson.enqueue(new Callback<SearchWrapper>() {

                    @Override
                    public void onFailure(Call<SearchWrapper> call, Throwable t) {
                        Log.e("MainActivity", "onFailure = " + t.getMessage());
                    }

                    @Override
                    public void onResponse(Call<SearchWrapper> call, Response<SearchWrapper> response) {
                        assert response.body() != null;
                        List<Film> filmList = response.body().results;

                        Intent intent = new Intent(SearchActivity.this, ListFilmActivity.class);
                        intent.putParcelableArrayListExtra("FilmList", (ArrayList<? extends Parcelable>) filmList);
                        startActivity(intent);


                    }
                });
            }

        });


        radioFilterGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroupFilter.clearCheck();
                cardViewFilter.setVisibility(View.INVISIBLE);
                cardViewGenres.setVisibility(View.VISIBLE);
            }
        });

        final ImageButton buttonCloseGenres = (ImageButton) findViewById(R.id.imageButtonCloseGenres);
        buttonCloseGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewGenres.setVisibility(View.INVISIBLE);
                cardViewFilter.setVisibility(View.VISIBLE);
            }

        });


        RadioGroupGenre1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton rB = findViewById(checkedId);

                int idGenre = Util.genreStringToInt(rB.getText().toString());
                rB.setChecked(false);


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.themoviedb.org/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                // on générée l'interface => on dit donc :  tu me generes une implémentation de la class filmDbApi que l'on va stocker dans service
                filmDbApi service = retrofit.create(filmDbApi.class);


                // utilisation d'un des services => ici test avec trois services
                Call<SearchWrapper> callJson4 = service.searchGenre("d0f80747d8ac43db918936f4a3d09e9c", "fr", "popularity.desc", 1, idGenre);

                callJson4.enqueue(new Callback<SearchWrapper>() {

                    @Override
                    public void onFailure(Call<SearchWrapper> call, Throwable t) {
                        Log.e("MainActivity", "onFailure = " + t.getMessage());
                    }

                    @Override
                    public void onResponse(Call<SearchWrapper> call, Response<SearchWrapper> response) {
                        assert response.body() != null;
                        List<Film> filmList = response.body().results;

                        Intent intent = new Intent(SearchActivity.this, ListFilmActivity.class);
                        intent.putParcelableArrayListExtra("FilmList", (ArrayList<? extends Parcelable>) filmList);
                        startActivity(intent);

                    }
                });
            }
        });

        RadioGroupGenre2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton rB = findViewById(checkedId);

                int idGenre = Util.genreStringToInt(rB.getText().toString());
                rB.setChecked(false);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.themoviedb.org/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                // on générée l'interface => on dit donc :  tu me generes une implémentation de la class filmDbApi que l'on va stocker dans service
                filmDbApi service = retrofit.create(filmDbApi.class);


                // utilisation d'un des services => ici test avec trois services
                Call<SearchWrapper> callJson4 = service.searchGenre("d0f80747d8ac43db918936f4a3d09e9c", "fr", "popularity.desc", 1, idGenre);

                callJson4.enqueue(new Callback<SearchWrapper>() {

                    @Override
                    public void onFailure(Call<SearchWrapper> call, Throwable t) {
                        Log.e("MainActivity", "onFailure = " + t.getMessage());
                    }

                    @Override
                    public void onResponse(Call<SearchWrapper> call, Response<SearchWrapper> response) {
                        assert response.body() != null;
                        List<Film> filmList = response.body().results;

                        Intent intent = new Intent(SearchActivity.this, ListFilmActivity.class);
                        intent.putParcelableArrayListExtra("FilmList", (ArrayList<? extends Parcelable>) filmList);
                        startActivity(intent);
                    }
                });
            }
        });


    }
}

