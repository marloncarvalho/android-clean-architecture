package io.marlon.cleanarchitecture.ui.mvp.login

import io.marlon.cleanarchitecture.domain.usecase.login.Login
import io.marlon.cleanarchitecture.ui.mvp.View
import timber.log.Timber
import javax.inject.Inject

open class LoginPresenter @Inject constructor(
        private val login: Login) : LoginContract.Presenter {
    lateinit var view: LoginContract.View

    override fun attach(view: View) {
        this.view = view as LoginContract.View
    }

    override fun destroy() {
    }

    override fun bootstrap() {
    }

    override fun login(username: String, password: String) {
        login.execute(
                params = Login.Param(username, password),
                onError = { error ->
                    Timber.d("Erro login")
                },
                onSuccess = { user ->
                    Timber.d("Sucesso login")
                }
        )
    }

}