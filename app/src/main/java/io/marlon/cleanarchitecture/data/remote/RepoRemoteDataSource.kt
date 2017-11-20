package io.marlon.cleanarchitecture.data.remote

import io.marlon.cleanarchitecture.data.converter.RepoRemoteConverter
import io.marlon.cleanarchitecture.data.remote.api.services.repo.RepoAPI
import io.marlon.cleanarchitecture.domain.datasource.RepoDataSource
import io.marlon.cleanarchitecture.domain.model.Repo
import io.reactivex.Flowable
import javax.inject.Inject

class RepoRemoteDataSource @Inject constructor(
        private val repoAPI: RepoAPI,
        private val converter: RepoRemoteConverter) : RepoDataSource {

    override fun save(list: List<Repo>) {
        throw UnsupportedOperationException()
    }

    override fun getRepos(username: String): Flowable<List<Repo>> =
            RxErrors.handle(repoAPI.getRepos(username)).map { converter.convert(it) }

}