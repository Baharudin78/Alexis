package com.alexis.shop.di

import android.content.Context
import com.alexis.shop.data.remote.network.ApiService
import com.alexis.shop.data.remote.network.AuthInterceptor
import com.alexis.shop.domain.usecase.auth.AuthInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(context : Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
//        return OkHttpClient.Builder()
//            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//            .connectTimeout(120, TimeUnit.SECONDS)
//            .readTimeout(120, TimeUnit.SECONDS)
//            .build()
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        return OkHttpClient.Builder()
//            .addInterceptor(AuthInteractor(context))
//            .addInterceptor(interceptor)
//            .connectTimeout(120, TimeUnit.SECONDS)
//            .readTimeout(120, TimeUnit.SECONDS)
//            .build()
    }

    @Provides
    fun provideApiService(client: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.myalexis.xyz:3001/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}