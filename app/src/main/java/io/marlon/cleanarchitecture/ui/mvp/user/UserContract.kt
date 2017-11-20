package io.marlon.cleanarchitecture.ui.mvp.user

import io.marlon.cleanarchitecture.domain.model.User
import io.marlon.cleanarchitecture.ui.mvp.ErrorView
import io.marlon.cleanarchitecture.ui.mvp.LoadingView

interface UserContract {

    interface Presenter : io.marlon.cleanarchitecture.ui.mvp.Presenter<View> {
        fun loadUser(text: String)
    }

    interface View : io.marlon.cleanarchitecture.ui.mvp.View, ErrorView, LoadingView {
        fun showUser(user: User)
    }

}