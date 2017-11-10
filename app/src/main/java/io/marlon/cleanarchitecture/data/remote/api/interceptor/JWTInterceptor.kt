package io.marlon.cleanarchitecture.data.remote.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class JWTInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().build()
        return chain.proceed(request)
    }
}