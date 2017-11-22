package io.marlon.cleanarchitecture.domain.usecase.repo

import io.marlon.cleanarchitecture.data.RepoRepository
import io.marlon.cleanarchitecture.domain.model.Repo
import io.marlon.cleanarchitecture.domain.usecase.UseCase
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class GetRepos @Inject constructor(
        private val repoRepository: RepoRepository) : UseCase.RxObservable<List<Repo>, String>() {

    override fun build(params: String?): Observable<List<Repo>> = repoRepository.getRepos(params!!)

}