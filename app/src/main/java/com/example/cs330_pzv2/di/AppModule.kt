package com.example.cs330_pzv2.di

import com.example.cs330_pzv2.common.Constants
import com.example.cs330_pzv2.data.remote.AnimeApi
import com.example.cs330_pzv2.data.repository.AnimeRepositoryImpl
import com.example.cs330_pzv2.domain.repository.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesAnimeApi(): AnimeApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimeApi::class.java)
    }

    @Provides
    @Singleton
    fun providesAnimeRepository(api: AnimeApi): AnimeRepository{
        return AnimeRepositoryImpl(api)
    }

}