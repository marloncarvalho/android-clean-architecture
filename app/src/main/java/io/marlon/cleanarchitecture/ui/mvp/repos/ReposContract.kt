package io.marlon.cleanarchitecture.ui.mvp.repos

import io.marlon.cleanarchitecture.domain.model.Repo
import io.marlon.cleanarchitecture.ui.mvp.ErrorView
import io.marlon.cleanarchitecture.ui.mvp.LoadingView

interface ReposContract {

    interface View : io.marlon.cleanarchitecture.ui.mvp.View, ErrorView, LoadingView {
        fun showRepos(repos: List<Repo>)
        fun noDataToShow()
    }

    interface Presenter : io.marlon.cleanarchitecture.ui.mvp.Presenter<View> {
        fun refreshRepos()
    }

}