package io.marlon.cleanarchitecture.ui.mvp.user

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import io.marlon.cleanarchitecture.domain.exception.ModelNotFound
import io.marlon.cleanarchitecture.domain.model.User
import io.marlon.cleanarchitecture.domain.usecase.user.GetUserDetails
import io.marlon.cleanarchitecture.ui.mvp.BasePresenter
import io.marlon.cleanarchitecture.ui.mvp.ViewErrorHandler
import timber.log.Timber
import javax.inject.Inject

class UserPresenter @Inject constructor(
        private val eh: ViewErrorHandler,
        private val getUser: GetUserDetails) : BasePresenter<UserContract.View>(), UserContract.Presenter {

    override fun loadUser(user: String) {
        view.showLoading()
        getUser.execute(
                params = user,
                onNext = { onGetUser(it) },
                onError = { onGetUserError(it) }
        )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun destroy() {
        getUser.clear()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun init() {
        Timber.d("Initializing View. OnResume event.")
    }

    private fun onGetUserError(throwable: Throwable) {
        Timber.d("Error loading user!")

        view.hideLoading()

        if (!eh.handle(view, throwable)) {
            if (throwable is ModelNotFound) {
                view.showError("User not found.")
            }
        }
    }

    private fun onGetUser(user: User) {
        Timber.d("OnNext -> Presenter got an user! ${user.login}, ${user.name}, ${user.id}")
        view.showUser(user!!)
        view.hideLoading()
    }

}