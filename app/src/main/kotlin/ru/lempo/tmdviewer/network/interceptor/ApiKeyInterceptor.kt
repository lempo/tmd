package ru.lempo.tmdviewer.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Author: Andrey Khitryy
 * Email: andrey.khitryy@gmail.com
 */
class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", "d45bcef740f5aa4cc4de87a477ed5641")
                .addQueryParameter("language", "en")
                .build()

        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
                .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}