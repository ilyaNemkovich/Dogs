package com.example.dogs.di.module

import android.util.Log
import com.example.dogs.BuildConfig
import com.example.dogs.data.network.DogApi
import com.example.dogs.di.annotations.ApplicationScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    fun provideRetrofit(httpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit =
        Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    @Provides
    @ApplicationScope
    fun provideGson(): Gson =
        GsonBuilder().serializeNulls().create()

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @ApplicationScope
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger { Log.i("Retrofit ", it) })
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        return interceptor
    }

    @Provides
    @ApplicationScope
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    @ApplicationScope
    fun provideGroupApi(retrofit: Retrofit): DogApi =
        retrofit.create(DogApi::class.java)

    companion object {
        private const val BASE_URL = "https://dog.ceo/"
        private const val CONNECT_TIMEOUT = 60L
    }
}