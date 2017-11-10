package io.marlon.cleanarchitecture.ui.mvp.user

import io.marlon.cleanarchitecture.domain.exception.ModelNotFound
import io.marlon.cleanarchitecture.domain.model.User
import io.marlon.cleanarchitecture.domain.usecase.user.GetUserDetails
import io.marlon.cleanarchitecture.domain.usecase.user.RxGetUserDetails
import io.marlon.cleanarchitecture.ui.error.ErrorHandler
import io.marlon.cleanarchitecture.ui.mvp.View
import timber.log.Timber
import javax.inject.Inject

class UserPresenter @Inject constructor(
        private val eh: ErrorHandler,
        private val getUser: RxGetUserDetails) : UserContract.Presenter {

    lateinit var view: UserContract.View

    override fun loadUser(user: String) {
        view.showLoading()
        getUser.execute(
                params = user,
                onNext = { onGetUser(it) },
                onError = { onGetUserError(it) }
        )
    }

    override fun attach(view: View) {
        this.view = view as UserContract.View
    }

    override fun destroy() {
        getUser.clear()
    }

    override fun bootstrap() {
    }

    private fun onGetUserError(throwable: Throwable) {
        Timber.d("Error loading user!")

        view.hideLoading()

        if (!eh.handle(view, throwable)) {
            if (throwable is ModelNotFound) {
                view.showError("User not found.")
            } else {
                view.showStrangeErrorMessage()
            }
        }
    }

    private fun onGetUser(user: User) {
        Timber.d("OnNext -> User found: ${user.login}, ${user.name}, ${user.id}")
        view.showUser(user!!)
        view.hideLoading()
    }

}