package io.marlon.cleanarchitecture.ui.mvp

interface Presenter {
    fun attach(view: View)
    fun destroy()
    fun bootstrap()
}