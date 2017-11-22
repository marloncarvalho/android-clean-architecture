package io.marlon.cleanarchitecture.domain.datasource

import io.marlon.cleanarchitecture.domain.model.Repo
import io.reactivex.Observable

interface RepoDataSource {
    fun getRepos(username: String): Observable<List<Repo>>
    fun save(list: List<Repo>)
}