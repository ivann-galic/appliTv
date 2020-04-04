package com.ivann.applicationtvgoodgit;

import android.os.Parcel;
import android.os.Parcelable;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/*
cette classe permet de créer l'objet film à partir des données récupérées sur les api.
Il est parcelisé pour pourvoir passer d'une vue à l'autre. Pour l'instant, pas de casting, ni de real.
 */
@Entity
public class Film implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int idDb;
    public int idFilm;
    public String filmImage;
    public String titre;
    public String dateSortie;
    public String Genre;
    public String resume;
    public String popularite;
    public boolean isFavorite = false;
    public boolean watchListed = false;


    public Film(int idFilm, String filmImage, String titre, String dateSortie, String genre, String resume, String popularite, boolean isFavorite, boolean watchListed) {
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

    protected Film(Parcel in) {
        idFilm = in.readInt();
        filmImage = in.readString();
        titre = in.readString();
        dateSortie = in.readString();
        Genre = in.readString();
        resume = in.readString();
        popularite = in.readString();
        isFavorite = in.readByte() != 0;
        watchListed = in.readByte() != 0;
    }

    public Film() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idFilm);
        dest.writeString(filmImage);
        dest.writeString(titre);
        dest.writeString(dateSortie);
        dest.writeString(Genre);
        dest.writeString(resume);
        if (popularite.contains(".")) {
            String separator = ".";
            int i = popularite.lastIndexOf(separator);
            String v = popularite.substring(0, i);
            popularite = v;
        }
        dest.writeString(popularite);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
        dest.writeByte((byte) (watchListed ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

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
}