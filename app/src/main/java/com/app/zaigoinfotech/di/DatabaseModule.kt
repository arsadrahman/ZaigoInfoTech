package com.app.zaigoinfotech.di

import android.content.Context
import android.content.SharedPreferences
import com.app.zaigoinfotech.utility.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(Constants.sharedPreferencesName, Context.MODE_PRIVATE)
}