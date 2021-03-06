package io.marlon.cleanarchitecture.data.remote.api.services.repo

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoAPI {

    @GET("users/{username}/repos")
    fun getRepos(@Path("username") username: String): Observable<List<RepoResponse>>

}