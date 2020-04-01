package com.ivann.applicationtvgoodgit;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SearchActivity extends AppCompatActivity {
    ArrayList<Film> films;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        loadDataFromApi();

    }


    /*
    Cette fonction traduit les id_genre donnés par l'api en string exploitables pour l'affichage dans le recyclerview
     */
    public String genreToString(int number) {

        switch (number) {

            case 28:
                return "Action";

            case 12:
                return "Aventure";

            case 16:
                return "Animation";

            case 35:
                return "Comédie";

            case 80:
                return "Crime";

            case 99:
                return "Documentaire";

            case 18:
                return "Drame";

            case 10751:
                return "Familial";

            case 14:
                return "Fantastique";

            case 36:
                return "Histoire";

            case 27:
                return "Horreur";

            case 10402:
                return "Musique";

            case 9648:
                return "Mystère";

            case 10749:
                return "Romance";

            case 878:
                return "Science-fiction";

            case 10770:
                return "Téléfilm";

            case 53:
                return "Thriller";

            case 10752:
                return "Guerre";

            case 37:
                return "Western";

            default:
                return "erreur de catégorie";


        }


    }
    /*---------------------------------------------------GESTION API ---------------------------------------------------*/

    private void loadDataFromApi() {
        //    String userChoice = userchoice();

        // construction d'un client  apartir des données fournies par retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // on générée l'interface => on dit donc :  tu me generes une implémentation de la class filmDbApi que l'on va stocker dans service
        filmDbApi service = retrofit.create(filmDbApi.class);

        // utilisation du service
        //Call searchedMovies =//
        Call<SearchWrapper> callJson = service.searchMovies("d0f80747d8ac43db918936f4a3d09e9c", "fr", "popularity.desc", 1);
        //SearchWrapper body =

        // on execute la requete
        callJson.enqueue(new Callback<SearchWrapper>() {

            @Override
            public void onFailure(Call<SearchWrapper> call, Throwable t) {
                Log.e("MainActivity", "onFailure = " + t.getMessage());
            }

            @Override
            public void onResponse(Call<SearchWrapper> call, Response<SearchWrapper> response) {

                List<Film> filmList = response.body().results;

// ------------------------------------- jusqu'ici y'a une liste=> code a revoir apres ---------------------//


                final CardView cardViewGenres = (CardView) findViewById(R.id.CardViewGenres);
                final Button buttonFilter = findViewById(R.id.buttonFilter);
                final CardView cardViewFilter = findViewById(R.id.CardViewFilter);
                
                // Lors d'un clic sur l'option "genre", une cardView (menu des filtres) prend la place pour afficher tous les genres.
                buttonFilter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cardViewFilter.setVisibility(View.VISIBLE);
                    }
                });

                // les boutons pour gérer les catégories
                final RadioGroup RadioGroupGenre1 = (RadioGroup) findViewById(R.id.RadioGroupGenre1);
                final RadioButton radioClickedGenre1 = (RadioButton) findViewById(RadioGroupGenre1.getCheckedRadioButtonId());
                final RadioGroup RadioGroupGenre2 = (RadioGroup) findViewById(R.id.RadioGroupGenre2);
                final RadioButton radioClickedGenre2 = (RadioButton) findViewById(RadioGroupGenre2.getCheckedRadioButtonId());

                final RadioGroup RadioGroupFilter = (RadioGroup) findViewById(R.id.RadioGroupFilter);
                final RadioButton radioFilterGenre = (RadioButton) findViewById(R.id.radioButtonGenre);
                // Lors d'un clic sur le bouton fermer (croix), une cardView prend la place pour afficher de nouveau
                // les options de filtres.
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
                        RadioGroupGenre1.clearCheck();
                        RadioGroupGenre2.clearCheck();
                    }

                });


                // l'intent permet de btranférer des informations de notre classe à ListFilmactivity
                Intent intent = new Intent(SearchActivity.this, ListFilmActivity.class);
                intent.putExtra("FilmList", filmList);
                startActivity(intent);

                // log d etest pour voir si cela a marché
                Log.i("MainActivity", "la list de sfilms " + filmList.toString());
                // System.out.println(film1.getString("title"));

            }
        });
    }




}





        // gestion de l'affichage


        //    Log.i("MainActivity", "le body result est = +"body.results);








        /*

        // on se connecte à la bdd
        OkHttpClient client = new OkHttpClient();
        // on obtient une reponse
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/discover/movie?api_key=d0f80747d8ac43db918936f4a3d09e9c&language=fr&sort_by=popularity.desc&page=1")
                .build();

        //on met les requetes en attente, pour éviter de bloquer la vue en cas de probleme avec l'api
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("MainActivity", "onFailure", e);
            }
            // on traite la réponse
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String body = response.body().string();
                // gestion de l'affichage
                final CardView cardViewGenres = (CardView) findViewById(R.id.CardViewGenres);
                final Button buttonFilter = findViewById(R.id.buttonFilter);
                final CardView cardViewFilter = findViewById(R.id.CardViewFilter);
                // Lors d'un clic sur l'option "genre", une cardView (menu des filtres) prend la place pour afficher tous les genres.
                buttonFilter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cardViewFilter.setVisibility(View.VISIBLE);
                    }
                });

                // les boutons pour gérer les catégories
                final RadioGroup RadioGroupGenre1 = (RadioGroup) findViewById(R.id.RadioGroupGenre1);
                final RadioButton radioClickedGenre1 = (RadioButton) findViewById(RadioGroupGenre1.getCheckedRadioButtonId());
                final RadioGroup RadioGroupGenre2 = (RadioGroup) findViewById(R.id.RadioGroupGenre2);
                final RadioButton radioClickedGenre2 = (RadioButton) findViewById(RadioGroupGenre2.getCheckedRadioButtonId());

                final RadioGroup RadioGroupFilter = (RadioGroup) findViewById(R.id.RadioGroupFilter);
                final RadioButton radioFilterGenre= (RadioButton) findViewById(R.id.radioButtonGenre);
                // Lors d'un clic sur le bouton fermer (croix), une cardView prend la place pour afficher de nouveau
                // les options de filtres.
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
                        RadioGroupGenre1.clearCheck();
                        RadioGroupGenre2.clearCheck();
                    }
                });

                // gestion de la réponse de l'api => on parse la réponse (body)
                try {

                    JSONObject jsonBody = new JSONObject(body);
                    // on cherche les éléments présents dans result
                    JSONArray results = jsonBody.getJSONArray("results");

                    // on créée une liste de films vide
                    ArrayList<Film> filmList = new ArrayList();

                    // boucle pour enregistrer les films dans une liste

                    for (int i = 0; i < results.length(); i++) {
                        JSONObject film1 = results.getJSONObject(i);

                        int idFilm = film1.getInt("id");
                        String filmImageUrlTronquee = film1.getString("poster_path");
                        String filmImage = "https://image.tmdb.org/t/p/original/" + filmImageUrlTronquee;
                        Log.i("MainActivity", "filmImage" + filmImage);

                        String titre = film1.getString("original_title");


                        String dateSortie = film1.getString("release_date");


                        String resume = film1.getString("overview");


                        JSONArray idGenre = film1.getJSONArray("genre_ids");
                        int idPremierGenre = (int) idGenre.get(0);

                        float popularite = film1.getLong("popularity");

                        filmList.add(new Film(idFilm, filmImage, titre, dateSortie, genreToString(idPremierGenre), resume, popularite));

                    }

                    // l'intent permet de btranférer des informations de notre classe à ListFilmactivity
                    Intent intent = new Intent(SearchActivity.this, ListFilmActivity.class);
                    intent.putExtra("FilmList", filmList);
                    startActivity(intent);

                    // log d etest pour voir si cela a marché
                    Log.i("MainActivity", "la list de sfilms " + filmList.toString());
                    // System.out.println(film1.getString("title"));



                } catch (JSONException e) {
                    e.printStackTrace();
                    e.getMessage();
                }

                Log.i("MainActivity", "onResponse : body=" + body);

                Log.i("MainActivity", "Started HTTP REquest");
            }
        });*/
  //  }


    //--------------- traitement de l'input de l'utilisateur ------------------------------------//
   // private String userChoice() {


            // je recupère l'entrée de l'utilisateur
       // final ImageButton buttonCloseGenres = (ImageButton) findViewById(R.id.imageButtonCloseGenres);
       // EditText tonEdit = (EditText)findViewById(R.id.textViewSearchTitle);

      //  String tonTexte = tonEdit.getText().toString();

        // bouton de validation qui appelle la requête

//        }