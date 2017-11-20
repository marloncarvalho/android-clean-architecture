package io.marlon.cleanarchitecture.ui.mvp.login

import io.marlon.cleanarchitecture.ui.mvp.ErrorView
import io.marlon.cleanarchitecture.ui.mvp.LoadingView

interface LoginContract {

    interface Presenter : io.marlon.cleanarchitecture.ui.mvp.Presenter<View> {
        fun login(username: String, password: String)
    }

    interface View : io.marlon.cleanarchitecture.ui.mvp.View, ErrorView, LoadingView {

    }

}