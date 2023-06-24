package com.example.cs330_pzv2.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.cs330_pzv2.common.Constants
import com.example.cs330_pzv2.data.database.AnimeDetailDatabase
import com.example.cs330_pzv2.data.database.dao.AnimeDetailDao
import com.example.cs330_pzv2.data.remote.AnimeApi
import com.example.cs330_pzv2.data.repository.AnimeDatabaseRepositoryImpl

import com.example.cs330_pzv2.domain.repository.AnimeDatabaseRepository

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

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideAnimeDetailDatabase(context: Context): AnimeDetailDatabase {
        return Room.databaseBuilder(
            context,
            AnimeDetailDatabase::class.java,
            "anime_detail_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAnimeDetailDao(database: AnimeDetailDatabase): AnimeDetailDao {
        return database.dao
    }


    @Provides
    @Singleton
    fun provideAnimeDatabaseRepository(db: AnimeDetailDatabase) : AnimeDatabaseRepository{
        return AnimeDatabaseRepositoryImpl(db.dao)
    }

}