package io.marlon.cleanarchitecture.data.remote.api.services.user

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UserAPI {

    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Maybe<UserResponse>

    @GET("users")
    fun getUsers(): Flowable<List<UserResponse>>

    @POST("/")
    fun login(@Header("Authorization") authorization: String): Single<String>
}