package io.marlon.cleanarchitecture.ui.mvp

interface View {

    fun init(presenter: Presenter<View>) {
        presenter.attach(this)
        presenter.init()
    }

}