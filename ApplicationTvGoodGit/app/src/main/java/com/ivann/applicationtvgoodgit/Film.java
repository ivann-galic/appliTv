package com.ivann.applicationtvgoodgit;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

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
    public String popularite;

    //----------------------- CONSTRUCTOR----------------------------------//
    protected Film(Parcel in) {
        idFilm = in.readInt();
        filmImage = in.readString();
        titre = in.readString();
        dateSortie = in.readString();
        Genre = in.createStringArrayList();
        resume = in.readString();
        popularite = in.readString();
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

    public String getPopularite() {
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

    public void setPopularite(String popularite) {
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
        if (popularite.contains(".")) {
            String separator =".";
            int i = popularite.lastIndexOf(separator);
            String v = popularite.substring(0,i);
            popularite = v;
        }
        Log.i("SearchA", "popularite après sub: " + popularite);

/*        long longPopularite = Long.parseLong(popularite);
        Log.i("SearchA", "longPopularite: " + longPopularite);
        BigDecimal bd = new BigDecimal(longPopularite);
        bd= bd.setScale(3,BigDecimal.ROUND_DOWN);
        longPopularite = bd.longValue();

        popularite = String.valueOf(longPopularite);*/
        dest.writeString(popularite);

        /*        float f = Float.parseFloat(popularite);
        Log.i("SearchA", "f: " + f);
        BigDecimal bd = new BigDecimal(f);
        bd= bd.setScale(0,BigDecimal.ROUND_DOWN);
        f = bd.floatValue();

        popularite = String.valueOf(f);*/

/*        Log.i("SearchA", "popularite: " + popularite);

        String separator =".";
        int i = popularite.lastIndexOf(separator);
        String v = popularite.substring(0,i);
        popularite = v;
        Log.i("SearchA", "popularite après sub: " + popularite);*/
    }
}
