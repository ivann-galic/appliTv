package com.ivann.applicationtvgoodgit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface filmDbApi {
/*
cette interface prépare le srequest et le sréponses
 */

    //URL
// https://api.themoviedb.org/3/search/movie/AVENGERS/?
// api_key=<<api_key>>&language=en-US&
// query=avengers&page=1&include_adult=false
        // Si le paramètre est dans l'URL => c'est un PATH
        // Si le paramètre est après le ? => c'est une QUERY PARAM

  //  https://api.themoviedb.org/3/discover/movie?api_key=d0f80747d8ac43db918936f4a3d09e9c&language=fr&sort_by=popularity.desc&page=1"

        @GET("3/discover/movie?")
        public Call<SearchWrapper> searchCategory(
                @Query("api_key") String apiKey,
                @Query("language") String language,
                @Query("sort_by") String tri,
                @Query("page") int page);


        // https://api.themoviedb.org/3/search/movie?api_key=d0f80747d8ac43db918936f4a3d09e9c&language=fr&query=star&page=1&include_adult=false
        @GET("3/search/movie?")
        public Call<SearchWrapper> searchMovies(
                @Query("api_key") String apiKey,
                @Query("language") String language,
                @Query("query") String searchText,
                @Query("page") int page);



        //https://api.themoviedb.org/3/discover/movie?api_key=d0f80747d8ac43db918936f4a3d09e9c&language=fr&sort_by=popularity.desc&include_adult=false&page=1&with_genres=28


        @GET("3/discover/movie?")
        public Call<SearchWrapper> searchGenre(
                @Query("api_key") String apiKey,
                @Query("language") String language,
                @Query("sort_by") String tri,
                @Query("page") int page,
                @Query("with_genres") int genreId);

       // https://api.themoviedb.org/3/discover/movie?api_key=d0f80747d8ac43db918936f4a3d09e9c&language=fr&sort_by=release_date.desc&include_adult=false&include_video=false&page=1&primary_release_date.gte=1920
        @GET("3/discover/movie?")
        public Call<SearchWrapper> searchDate(
                @Query("api_key") String apiKey,
                @Query("language") String language,
                @Query("sort_by") String tri,
                @Query("page") int page,
                @Query("primary_release_date.gte") int dateId);


}
