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
    public final int idFilm;
    @SerializedName("poster_path")
    public final String filmImage;
    @SerializedName("title")
    public final String titre;
    @SerializedName("release_date")
    public final String dateSortie;
    @SerializedName("genre_ids")
    public final ArrayList<String> Genre;
    @SerializedName("overview")
    public final String resume;
    @SerializedName("popularity")
    public final float popularite;

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
