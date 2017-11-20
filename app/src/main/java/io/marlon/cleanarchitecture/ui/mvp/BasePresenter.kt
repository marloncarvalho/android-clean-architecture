package io.marlon.cleanarchitecture.ui.mvp

abstract class BasePresenter<V : View> : Presenter<V> {
    lateinit var view: V

    override fun attach(view: V) {
        this.view = view
    }

}