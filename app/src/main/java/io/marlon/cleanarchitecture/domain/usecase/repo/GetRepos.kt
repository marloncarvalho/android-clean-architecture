package io.marlon.cleanarchitecture.domain.usecase.repo

import io.marlon.cleanarchitecture.data.RepoRepository
import io.marlon.cleanarchitecture.data.remote.exception.ResourceNotFoundException
import io.marlon.cleanarchitecture.domain.exception.ModelNotFound
import io.marlon.cleanarchitecture.domain.model.Repo
import io.marlon.cleanarchitecture.domain.usecase.UseCase
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class GetRepos @Inject constructor(
        private val repoRepository: RepoRepository) : UseCase.RxObservable<List<Repo>, String>() {

    override fun build(params: String?): Observable<List<Repo>> {
        return repoRepository.getRepos(params!!).onErrorResumeNext(Function { throwable ->
            if (throwable is ResourceNotFoundException) {
                Observable.error(ModelNotFound(""))
            } else {
                Observable.error(throwable)
            }
        })
    }

}