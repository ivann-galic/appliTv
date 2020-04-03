package com.ivann.applicationtvgoodgit;

import android.os.Parcel;
import android.os.Parcelable;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/*
cette classe permet de créer l'objet film à partir des données récupérées sur les api.
Il est parcelisé pour pourvoir passer d'une vue à l'autre. Pour l'instant, pas de casting, ni de real.
 */
//@Entity
public class FilmCleaned implements Parcelable {
  //  @PrimaryKey
    @SerializedName("id")
    public int idFilm;
    @SerializedName("poster_path")
    public String filmImage;
    @SerializedName("title")
    public String titre;
    @SerializedName("release_date")
    public String dateSortie;
    @SerializedName("genre_ids")
    public String Genre;
    @SerializedName("overview")
    public String resume;
    @SerializedName("popularity")
    public int popularite;
    @SerializedName("isFavorite")
    public boolean isFavorite = false;
    @SerializedName("watchListed")
    public boolean watchListed = false;

    /*Film(){

    }*/


    public FilmCleaned(int idFilm, String filmImage, String titre, String dateSortie, String genre, String resume, int popularite, boolean isFavorite, boolean watchListed) {
        this.idFilm = idFilm;
        this.filmImage = filmImage;
        this.titre = titre;
        this.dateSortie = dateSortie;
        this.Genre = genre;
        this.resume = resume;
        this.popularite = popularite;
        this.isFavorite = isFavorite;
        this.watchListed = watchListed;
    }

    protected FilmCleaned(Parcel in) {
        idFilm = in.readInt();
        filmImage = in.readString();
        titre = in.readString();
        dateSortie = in.readString();
        Genre = in.readString();
        resume = in.readString();
        popularite = in.readInt();
        isFavorite = in.readByte() != 0;
        watchListed = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idFilm);
        dest.writeString(filmImage);
        dest.writeString(titre);
        dest.writeString(dateSortie);
        dest.writeString(Genre);
        dest.writeString(resume);
        dest.writeInt(popularite);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
        dest.writeByte((byte) (watchListed ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FilmCleaned> CREATOR = new Creator<FilmCleaned>() {
        @Override
        public FilmCleaned createFromParcel(Parcel in) {
            return new FilmCleaned(in);
        }

        @Override
        public FilmCleaned[] newArray(int size) {
            return new FilmCleaned[size];
        }
    };
}