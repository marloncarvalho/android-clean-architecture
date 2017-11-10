package io.marlon.cleanarchitecture.data.remote.api.services.user

import io.reactivex.Flowable
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Path

interface UserAPI {

    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Maybe<UserResponse>

    @GET("users")
    fun getUsers(): Flowable<List<UserResponse>>

}