package com.bouraoui.startwars.data.repository

import com.bouraoui.startwars.data.model.FilmModel
import com.bouraoui.startwars.data.remote.MoviesApi
import com.bouraoui.startwars.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepository @Inject constructor() {

    @Inject
    lateinit var moviesApi: MoviesApi

    suspend fun getMovies(): Flow<DataState<FilmModel?>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val moviesList = moviesApi.getMoviesList()
            emit(DataState.Success(moviesList.body()))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }


}