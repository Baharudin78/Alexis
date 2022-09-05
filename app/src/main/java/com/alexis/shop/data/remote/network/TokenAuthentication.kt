package com.alexis.shop.data.remote.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

class TokenAuthentication constructor(
    private val accessToken : String,
    private val tokenType : String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", "$tokenType $accessToken")
            .build()
        return chain.proceed(request)
    }
}