package com.ivann.applicationtvgoodgit;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/*
cette classe permet de créer l'objet film à partir des données récupérées sur les api.
Il est parcelisé pour pourvoir passer d'une vue à l'autre. Pour l'instant, pas de casting, ni de real.
 */
public class Film implements Parcelable  {
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
    public float popularite;

    //----------------------- CONSTRUCTOR----------------------------------//
    protected Film(Parcel in) {
        idFilm = in.readInt();
        filmImage = in.readString();
        titre = in.readString();
        dateSortie = in.readString();
        Genre = in.createStringArrayList();
        resume = in.readString();
        popularite = in.readFloat();
    }



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

    public float getPopularite() {
        return popularite;
    }


    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public void setFilmImage(String filmImage) {
        this.filmImage = filmImage;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    public void setGenre(ArrayList<String> genre) {
        Genre = genre;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setPopularite(float popularite) {
        this.popularite = popularite;
    }


// ------------------- PARCEL METHODES -----------------------------------//



    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idFilm);
        dest.writeString(filmImage);
        dest.writeString(titre);
        dest.writeString(dateSortie);
        dest.writeStringList(Genre);
        dest.writeString(resume);
        dest.writeFloat(popularite);
    }
}
