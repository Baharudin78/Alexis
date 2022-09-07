package com.alexis.shop.data.remote.network

import android.content.Context
import com.alexis.shop.utils.prefs.SheredPreference
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context : Context) : Interceptor {
    private val shared = SheredPreference(context)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        shared.getToken().let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(requestBuilder.build())
    }
}