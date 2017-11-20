package io.marlon.cleanarchitecture.ui.mvp

import android.arch.lifecycle.LifecycleObserver

interface Presenter<in V : View> : LifecycleObserver {
    fun attach(view: V)
    fun destroy()
    fun init()
}