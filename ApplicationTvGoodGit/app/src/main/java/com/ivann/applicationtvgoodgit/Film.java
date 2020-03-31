package com.ivann.applicationtvgoodgit;

import android.os.Parcel;
import android.os.Parcelable;

/*
cette classe permet de créer l'objet film à partir des données récupérées sur les api.
Il est parcelisé pour pourvoir passer d'une vue à l'autre. Pour l'instant, pas de casting, ni de real.
 */
public class Film implements Parcelable  {

    public final int idFilm;
    public final String filmImage;
    public final String titre;
    public final String dateSortie;
    public final String Genre;
    public final String resume;
    public final float popularite;

// ----------------------------------- CONSTRUCTEUR ---------------------------------------------------------------------- //
    public Film(int idFilm, String filmImage, String titre, String dateSortie, String Genre, String resume, float popularite) {
        this.idFilm = idFilm;
        this.filmImage = filmImage;
        this.titre = titre;
        this.dateSortie = dateSortie;
        this.Genre = Genre;
        this.resume = resume;
        this.popularite = popularite;
    }

// ----------------------------------- PARCELISATION ---------------------------------------------------------------------- //

    protected Film(Parcel in) {
        idFilm = in.readInt();
        filmImage = in.readString();
        titre = in.readString();
        dateSortie = in.readString();
        Genre = in.readString();
        resume = in.readString();
        popularite = in.readFloat();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idFilm);
        parcel.writeString(filmImage);
        parcel.writeString(titre);
        parcel.writeString(dateSortie);
        parcel.writeString(Genre);
        parcel.writeString(resume);
        parcel.writeFloat(popularite);
    }



 //--------------------------------- GETTERS----------------------------------------------------//
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

    public String getGenre() {
        return Genre;
    }

    public String getResume() {
        return resume;
    }

    public float getPopularite() {
        return popularite;
    }

    @Override
    public String toString() {
        return "Film{" +
                "idFilm=" + idFilm +
                ", filmImage='" + filmImage + '\'' +
                ", titre='" + titre + '\'' +
                ", dateSortie='" + dateSortie + '\'' +
                ", Genre=" + Genre +
                ", resume='" + resume + '\'' +
                ", popularite=" + popularite +
                '}';
    }
}
