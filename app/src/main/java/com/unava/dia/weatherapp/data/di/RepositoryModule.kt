package com.unava.dia.weatherapp.data.di

import android.content.Context
import androidx.room.Room
import com.unava.dia.weatherapp.data.local.prefs.AppPrefs
import com.unava.dia.weatherapp.data.local.prefs.PrefsHelper
import com.unava.dia.weatherapp.domain.model.CurrentWeatherResponseDao
import com.unava.dia.weatherapp.domain.model.FutureWeatherResponseDao
import com.unava.dia.weatherapp.domain.repository.AppDatabase
import com.unava.dia.weatherapp.domain.repository.IWeatherRepository
import com.unava.dia.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun providePrefHelper(appPrefs: AppPrefs): PrefsHelper {
        return appPrefs
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext app: Context,
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "wDB"
    ).allowMainThreadQueries()
        .build()

    @Provides
    @Singleton
    fun provideCurrentWeatherDao(db: AppDatabase) = db.currentWeatherDao()

    @Provides
    @Singleton
    fun provideFutureWeatherDao(db: AppDatabase) = db.futureWeatherDao()

    @Provides
    @Singleton
    fun provideRepository(
        currentWeatherDao: CurrentWeatherResponseDao,
        futureWeatherDao: FutureWeatherResponseDao,
    ): IWeatherRepository =
        WeatherRepository(currentWeatherDao, futureWeatherDao)

}