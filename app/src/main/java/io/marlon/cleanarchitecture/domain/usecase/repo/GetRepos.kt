package io.marlon.cleanarchitecture.domain.usecase.repo

import io.marlon.cleanarchitecture.data.RepoRepository
import io.marlon.cleanarchitecture.domain.model.Repo
import io.marlon.cleanarchitecture.domain.usecase.UseCase
import io.reactivex.Flowable
import javax.inject.Inject

class GetRepos @Inject constructor(
        private val repoRepository: RepoRepository) : UseCase.RxFlowable<List<Repo>, String>() {

    override fun build(params: String?): Flowable<List<Repo>> = repoRepository.getRepos(params!!)

}