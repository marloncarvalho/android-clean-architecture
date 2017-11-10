package io.marlon.cleanarchitecture.data.remote.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class APIVersionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
            request = request.newBuilder()
                    .addHeader("Accept", "application/vnd.github.v3+json")
                    .build()

        return chain.proceed(request)
    }

}