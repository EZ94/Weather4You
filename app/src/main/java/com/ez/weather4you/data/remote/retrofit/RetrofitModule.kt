package com.ez.weather4you.data.remote.retrofit

import com.ez.weather4you.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private val json = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val currentUrl = chain.request().url
            val newUrl= currentUrl.newBuilder().addQueryParameter("key", BuildConfig.WEATHER_API_KEY).build()
            val currentRequest = chain.request().newBuilder()
            val newRequest = currentRequest.url(newUrl).build()
            chain.proceed(newRequest)
        }.build()

        return Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(json.asConverterFactory("application/json; charset=utf-8".toMediaType()))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): APIService= retrofit.create(APIService::class.java)
}