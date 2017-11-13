package io.marlon.cleanarchitecture.ui.mvp.login

import io.marlon.cleanarchitecture.ui.mvp.View
import javax.inject.Inject

open class LoginPresenter @Inject constructor() : LoginContract.Presenter {
    lateinit var view: LoginContract.View

    override fun attach(view: View) {
        this.view = view as LoginContract.View
    }

    override fun destroy() {
    }

    override fun bootstrap() {
    }

    override fun login(username: String, password: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}