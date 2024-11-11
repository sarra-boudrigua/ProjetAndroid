package com.example.tp1.ui.theme

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("trending/movie/week")
    suspend fun lastmovies(@Query("api_key") api_key: String, @Query("language") langage: String): moviesModel
    @GET("trending/tv/week")
    suspend fun lastseries(@Query("api_key") api_key: String, @Query("language") langage: String): seriesModel

    @GET("trending/person/week")
    suspend fun lastpersonnes(@Query("api_key") api_key: String, @Query("language") langage: String) : personModel

    @GET("movie/{id}")
    suspend fun movieDetails(@Path("id") id: String, @Query("api_key") api_key: String, @Query("language") langage: String): MovieDetailsModel

    @GET("tv/{id}")
    suspend fun serieDetails(@Path("id") id: String, @Query("api_key") api_key: String, @Query("language") langage: String): serieDetailsModel

    @GET("search/movie")
    suspend fun searchMovies(@Query("api_key") api_key: String, @Query("query") searchtext: String, @Query("language") langage: String): moviesModel

    @GET("search/tv")
    suspend fun searchSeries(@Query("api_key") api_key: String, @Query("query") searchtext: String, @Query("language") langage: String): seriesModel

    @GET("search/person")
    suspend fun searchActeur(@Query("api_key") api_key: String, @Query("query") searchtext: String, @Query("language") langage: String): personModel

    @GET("movie/{id}/credits")
    suspend fun ActeursMovie(@Path("id") id: String, @Query("api_key") api_key: String, @Query("language") langage: String): CastModel

    @GET("tv/{id}/credits")
    suspend fun ActeursSerie(@Path("id") id: String, @Query("api_key") api_key: String, @Query("language") langage: String): CastModel


}
