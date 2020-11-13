package com.bouraoui.startwars.data.remote

import com.bouraoui.startwars.data.model.FilmItemModel
import com.bouraoui.startwars.data.model.FilmModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject


interface MoviesApi {


    @GET("films/")
    suspend fun getMoviesList(): Response<FilmModel>

    @GET("films/{id}/")
    suspend fun getMoviesById(@Path("id") movieId: String): Response<FilmItemModel>
}