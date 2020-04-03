package com.ivann.applicationtvgoodgit.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ivann.applicationtvgoodgit.movieDb.FilmJson;
import com.ivann.applicationtvgoodgit.Film;

import java.util.List;

@Dao
public interface FilmDao {

    @Insert
    void insertFilm(Film film);

    //@Query("DELETE FROM film WHERE idFilm = ...")
    //List<Film> delFilm();

    @Query("SELECT * FROM film")
    List<Film> getAll();
}
