package com.bouraoui.startwars.data.remote

import com.bouraoui.startwars.di.ForApplication
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@ForApplication
@Module
class NetworkModule {

    @Provides
    fun provideLoginRetrofitService(): MoviesApi {
        return Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApi::class.java)
    }
}