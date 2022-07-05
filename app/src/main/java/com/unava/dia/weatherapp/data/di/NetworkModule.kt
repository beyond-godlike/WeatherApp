package com.unava.dia.weatherapp.data.di

import com.unava.dia.weatherapp.data.api.RetrofitFactory
import com.unava.dia.weatherapp.data.api.WeatherApi
import com.unava.dia.weatherapp.domain.usecase.GetCurrentWeatherUseCase
import com.unava.dia.weatherapp.domain.usecase.GetFutureWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideApi(): WeatherApi = RetrofitFactory.api()

    @Provides
    @Singleton
    fun provideGetCurrentWeatherUseCase(api: WeatherApi) = GetCurrentWeatherUseCase(api)

    @Singleton
    @Provides
    fun provideGetFutureWeatherUseCase(api: WeatherApi) = GetFutureWeatherUseCase(api)
}