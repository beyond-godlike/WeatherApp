package com.unava.dia.weatherapp.data.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitFactory {
    /**
     * интерсептор который помогает передать апи ключ
     */
    private var interceptor = Interceptor { chain ->
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("key", "72145f7d201a432c92a180346212112")
            .build()

        val newRequest = chain.request().newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val lg = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(lg)
        .addInterceptor(interceptor)
        .build()

    /**
     * возвращает объект ретрофита дл я запросов в сеть
     * для сереализации/десериализации используется Moshi
     */
    private fun retrofit(): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.weatherapi.com/v1/")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    /**
     * возвращает апи со всеми нужными функциями
     */
    fun api(): WeatherApi {
        return retrofit().create(WeatherApi::class.java)
    }
}