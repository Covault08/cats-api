package com.example.cat_api.data.interceptors

import com.example.cat_api.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().newBuilder().apply {
            addHeader("x-api-key", BuildConfig.apikey)
        }.build().also {
            return chain.proceed(it)
        }
    }
}