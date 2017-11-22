package io.marlon.cleanarchitecture.ui.mvp.repos

import io.marlon.cleanarchitecture.domain.model.Repo
import io.marlon.cleanarchitecture.domain.usecase.UseCaseObserver
import io.marlon.cleanarchitecture.domain.usecase.repo.GetRepos
import io.marlon.cleanarchitecture.ui.mvp.BasePresenter
import io.marlon.cleanarchitecture.ui.mvp.ViewErrorHandler
import timber.log.Timber
import javax.inject.Inject

open class ReposPresenter @Inject constructor(
        private val eh: ViewErrorHandler,
        private val getRepos: GetRepos) : BasePresenter<ReposContract.View>(), ReposContract.Presenter {

    override fun destroy() {
        getRepos.clear()
    }

    override fun init() {
        refreshRepos()
    }

    private fun onNextGetRepos(repos: List<Repo>) {
        Timber.d("Do we have repos to show? ${repos.count()}")

        if (repos.count() > 0) {
            view.showRepos(repos)
        } else {
            Timber.d("No repos to show! :-(")
            view.noDataToShow()
        }
    }

    private fun onErrorGetRepos(throwable: Throwable) {
        eh.handle(view, throwable)
        view.hideLoading()
    }

    private fun onCompleteGetRepos() {
        Timber.d("Completed")
        view.hideLoading()
    }

    override fun refreshRepos() {
        view.showLoading()

        getRepos.execute(object : UseCaseObserver.RxObservable<List<Repo>>() {

            override fun onComplete() {
                Timber.d("OnComplete")
                onCompleteGetRepos()
            }

            override fun onNext(value: List<Repo>) {
                Timber.d("OnNext: ${value.count()}")
                onNextGetRepos(value)
            }

            override fun onError(e: Throwable) {
                Timber.d("OnError: ${e}")
                onErrorGetRepos(e)
            }

            override fun onStart() {
                Timber.d("OnStart")
            }

        }, "marloncarvalho")
//        getRepos.execute(
//                params = "marloncarvalho",
//                onNext = { onNextGetRepos(it) },
//                onError = { onErrorGetRepos(it) },
//                onComplete = { onCompleteGetRepos() }
//        )
    }

}