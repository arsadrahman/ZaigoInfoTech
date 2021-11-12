package com.app.zaigoinfotech.di

import android.content.Context
import android.content.SharedPreferences
import android.location.Geocoder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MapModule {

    @Singleton
    @Provides
    fun provideCoder(@ApplicationContext context: Context): Geocoder = Geocoder(context)
}