package com.alexis.shop.di

import android.content.Context
import com.alexis.shop.utils.prefs.SheredPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    fun providesContext(@ApplicationContext context: Context): Context = context

    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context) : SheredPreference {
        return SheredPreference(context)
    }
}