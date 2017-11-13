package io.marlon.cleanarchitecture.data.remote.api.services.user

import io.marlon.cleanarchitecture.domain.model.User
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserAPI {

    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Maybe<UserResponse>

    @GET("users")
    fun getUsers(): Flowable<List<UserResponse>>

    @POST()
    fun login(username: String, password: String): Single<User>
}