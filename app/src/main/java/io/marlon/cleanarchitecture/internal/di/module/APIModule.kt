package io.marlon.cleanarchitecture.internal.di.module

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.marlon.cleanarchitecture.data.remote.api.interceptor.APIVersionInterceptor
import io.marlon.cleanarchitecture.data.remote.api.interceptor.JWTInterceptor
import io.marlon.cleanarchitecture.data.remote.api.services.repo.RepoAPI
import io.marlon.cleanarchitecture.data.remote.api.services.user.UserAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class APIModule {

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .client(client)
                .build()
    }

    @Provides
    fun provideHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(JWTInterceptor())
        httpClient.addInterceptor(APIVersionInterceptor())
//        httpClient.addInterceptor(logging)

        return httpClient.build()
    }

    @Provides
    fun provideUserAPI(retrofit: Retrofit): UserAPI = retrofit.create(UserAPI::class.java)

    @Provides
    fun provideRepoAPI(retrofit: Retrofit): RepoAPI = retrofit.create(RepoAPI::class.java)

}