package com.ivann.applicationtvgoodgit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivann.applicationtvgoodgit.database.FilmDao;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FocusFilmActivity extends AppCompatActivity {

    Film film;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_film);

        Intent srcIntent = getIntent();
        film = srcIntent.getParcelableExtra("film");
        final ImageButton imageButtonCoeur = findViewById(R.id.imageButtonCoeur);
        FilmDao dao = App.db.filmDao();

        final List<Film> favorisFilms = dao.getAll();


        Log.i("FocusActivity", "la list des films " + film);
        Log.i("FocusActivity", "la list des favoris " + favorisFilms);
        Log.i("FocusActivity", "la taille des favoris " + favorisFilms.size());


        Film filmFromFavorite;

        // si la liste des favoris n'est pas vide, on la parcours. Si notre film est dedans on le substitue
        // par celui qui est dans la liste (donc avec le statut isFavorite à jour) pour la suite de la procédure.
        if (favorisFilms.size() > 0) {
            for (int i = 0; i < favorisFilms.size(); i++) {
                filmFromFavorite = favorisFilms.get(i);
                if (filmFromFavorite.idFilm == film.idFilm) {
                    film = filmFromFavorite;
                    imageButtonCoeur.setImageResource(R.drawable.like);
                }
            }
        }

        imageButtonCoeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(favorisFilms.size() > 0 && film.isFavorite) {
                    imageButtonCoeur.setImageResource(R.drawable.no_like);
                    onFavoriteClicked();
                    Log.i("FocusFilm", "Like: " + film.isFavorite);
                } else {
                    imageButtonCoeur.setImageResource(R.drawable.like);
                    onFavoriteClicked();
                    Log.i("FocusFilm", "NoLike: " + film.isFavorite);
                }

            }
        });
        final TextView textViewTitle = findViewById(R.id.textViewTitle);
        assert film != null;
        textViewTitle.setText(film.titre);
        final TextView textViewParution = findViewById(R.id.textViewParution);
        textViewParution.setText("Date de sortie : " + film.dateSortie);
        final TextView textViewPopularite = findViewById(R.id.textViewPopularite);
        textViewPopularite.setText("Popularité : " + film.popularite);
        final TextView textViewResume = findViewById(R.id.textViewResume);
        textViewResume.setText(film.resume);
        Log.i("FocusActivity", "Résumé: " + film.resume);
        ImageView imageViewPoster = findViewById(R.id.imageViewPoster);
        Picasso.get().load("https://image.tmdb.org/t/p/original" + film.filmImage).into(imageViewPoster);// utilisation de la lib Picasso qui permet de choper une image via url ultra simplement
        final TextView textViewGenre = findViewById(R.id.textViewGenre);
        textViewGenre.setText("Genre : " + film.Genre);
    }

    private void onFavoriteClicked() {

        if (film.isFavorite){
            FilmDao filmDao = App.db.filmDao();
            int favId = film.idFilm;
            filmDao.delFilm(favId);
            film.isFavorite = false;
        } else {
            FilmDao filmDao = App.db.filmDao();
            film.isFavorite = true;
            filmDao.insertFilm(film);
        }
    }
}
