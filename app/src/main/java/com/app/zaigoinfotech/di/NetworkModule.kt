package com.app.zaigoinfotech.di

import android.content.Context
import android.content.SharedPreferences
import com.app.zaigoinfotech.repository.NetworkInterface
import com.app.zaigoinfotech.utility.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.baseURL)
        .build()

    @Singleton
    @Provides
    fun provideNetwork(retrofit: Retrofit): NetworkInterface =
        retrofit.create(NetworkInterface::class.java)


}