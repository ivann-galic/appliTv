package com.ivann.applicationtvgoodgit;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FilmDao {

    @Insert
    void insertFilm(FilmCleaned film);

    //@Query("DELETE FROM film WHERE idFilm = ...")
    //List<Film> delFilm();

    @Query("SELECT * FROM film")
    List<Film> getAll();
}
