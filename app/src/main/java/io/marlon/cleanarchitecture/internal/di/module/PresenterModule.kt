package io.marlon.cleanarchitecture.internal.di.module

import dagger.Binds
import dagger.Module
import io.marlon.cleanarchitecture.ui.mvp.login.LoginContract
import io.marlon.cleanarchitecture.ui.mvp.login.LoginPresenter
import io.marlon.cleanarchitecture.ui.mvp.user.UserContract
import io.marlon.cleanarchitecture.ui.mvp.user.UserPresenter

@Module
abstract class PresenterModule {

    @Binds
    abstract fun bindPresenterUser(presenter: UserPresenter): UserContract.Presenter

    @Binds
    abstract fun bindPresenterLogin(presenter: LoginPresenter): LoginContract.Presenter

}