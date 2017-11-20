package io.marlon.cleanarchitecture.ui.mvp

import android.arch.lifecycle.ViewModel


open class BaseViewModel<in V : View, P : Presenter<V>> : ViewModel() {
    var presenter: P? = null

    override fun onCleared() {
        super.onCleared()
        presenter?.destroy()
        presenter = null
    }

}