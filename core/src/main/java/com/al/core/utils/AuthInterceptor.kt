package com.al.core.utils

import com.al.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.API_KEY}")
            .build()
        return chain.proceed(request)
    }
}