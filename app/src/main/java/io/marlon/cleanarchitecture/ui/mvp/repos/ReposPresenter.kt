package io.marlon.cleanarchitecture.ui.mvp.repos

import io.marlon.cleanarchitecture.domain.model.Repo
import io.marlon.cleanarchitecture.domain.usecase.repo.GetRepos
import io.marlon.cleanarchitecture.ui.mvp.BasePresenter
import timber.log.Timber
import javax.inject.Inject

open class ReposPresenter @Inject constructor(
        private val getRepos: GetRepos
) : BasePresenter<ReposContract.View>(), ReposContract.Presenter {

    override fun destroy() {
        getRepos.clear()
    }

    override fun init() {
        refreshRepos()
    }

    private fun onNextGetRepos(repos: List<Repo>) {
        this.view.showRepos(repos)
    }

    private fun onErrorGetRepos(error: Throwable) {
        view.showError(error.message!!)
    }

    private fun onCompleteGetRepos() {
        Timber.d("Completed")
        view.hideLoading()
    }

    override fun refreshRepos() {
        view.showLoading()

        getRepos.execute(
                params = "marloncarvalho",
                onNext = { onNextGetRepos(it) },
                onError = { onErrorGetRepos(it) },
                onComplete = { onCompleteGetRepos() }
        )
    }

}