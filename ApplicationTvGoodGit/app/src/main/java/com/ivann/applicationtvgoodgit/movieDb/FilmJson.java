package com.ivann.applicationtvgoodgit.movieDb;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/*
cette classe permet de créer l'objet film à partir des données récupérées sur les api.
Il est parcelisé pour pourvoir passer d'une vue à l'autre. Pour l'instant, pas de casting, ni de real.
 */

public class FilmJson  {
    @SerializedName("id")
    public  int idFilm;
    @SerializedName("poster_path")
    public  String filmImage;
    @SerializedName("title")
    public String titre;
    @SerializedName("release_date")
    public  String dateSortie;
    @SerializedName("genre_ids")
    public  ArrayList<String> Genre;
    @SerializedName("overview")
    public  String resume;
    @SerializedName("popularity")
    public String popularite;

    //--------------------- GETTERS AND SETTERS--------------------------------//

    public int getIdFilm() {
        return idFilm;
    }

    public String getFilmImage() {
        return filmImage;
    }

    public String getTitre() {
        return titre;
    }

    public String getDateSortie() {
        return dateSortie;
    }

    public ArrayList<String> getGenre() {
        return Genre;
    }

    public String getResume() {
        return resume;
    }

    public String getPopularite() {
        return popularite;
    }
}
