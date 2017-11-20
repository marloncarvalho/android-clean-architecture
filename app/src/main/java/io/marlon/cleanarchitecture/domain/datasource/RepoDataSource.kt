package io.marlon.cleanarchitecture.domain.datasource

import io.marlon.cleanarchitecture.domain.model.Repo
import io.reactivex.Flowable

interface RepoDataSource {
    fun getRepos(username: String): Flowable<List<Repo>>
    fun save(list: List<Repo>)
}