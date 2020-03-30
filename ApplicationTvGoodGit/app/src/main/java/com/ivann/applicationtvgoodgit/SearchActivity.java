package com.ivann.applicationtvgoodgit;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;




    public class SearchActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search);
            loadDataFromApi();
        }

        private void loadDataFromApi() {
        //    String userChoice = userchoice();
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.themoviedb.org/3/discover/movie?api_key=d0f80747d8ac43db918936f4a3d09e9c&language=fr&sort_by=popularity.desc&page=1")
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Log.e("MainActivity", "onFailue", e);
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    String body = response.body().string();

                    try {

                        JSONObject jsonBody = new JSONObject(body);
                        JSONArray results = jsonBody.getJSONArray("results");

                        ArrayList<Film> filmList = new ArrayList();
                        // boucle pour enregistrer les films dans une liste

                        for (int i=0; i <=results.length();i++) {
                        JSONObject film1 = results.getJSONObject(i);
                        //JSONObject film2 = results.getJSONObject(1);

                        int idFilm = film1.getInt("id");
                        String filmImage = film1.getString("poster_path");
                        String titre = film1.getString("original_title");
                        String dateSortie =film1.getString("release_date");
                        String resume = film1.getString("overview");
                        JSONArray idGenre = film1.getJSONArray("genre_ids");
                        int idPremierGenre = (int)idGenre.get(i);
                        float popularite = film1.getLong("popularity");

                        Film film = new Film(idFilm,filmImage,titre,dateSortie,idPremierGenre,resume,popularite);
                        filmList.add(film);
                        }


                        //Log.i("MainActivity", "resultat film1 page = 1 " + film1);
                       // Log.i("MainActivity", "resultat film2 page = 1 " + film2);
                       // Log.i ("MainActivity", "id du film 1 page = 1 " + idFilm);
                        //Log.i("MainActivity","objet film : " + film.toString());
                        Log.i("MainActivity","la list de sfilms "+filmList);
                       // System.out.println(film1.getString("title"));

                       /* Movie movie1 = new Movie(film1.getString("title"),
                                                film1.getString("title"),

                                ))*/

                    } catch (JSONException e) {
                        e.printStackTrace();
                        e.getMessage();
                    }
                    //  System.out.println("je recup" + jsonArray[1]);
                       // Log.i("MainActivity", "onResponse : body=" + body);

                    //Log.i("MainActivity", "Started HTTP REquest");
                }
            });
        }




        // traitement de l'input de l'utilisateur
/*
        private String userChoice() {
            // je recupère l'entrée de l'utilisateur
            // bouton de validation qui appelle la requête

        });
        */


    }


