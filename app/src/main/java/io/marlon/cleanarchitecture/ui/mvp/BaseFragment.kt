package io.marlon.cleanarchitecture.ui.mvp

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dagger.android.support.DaggerFragment
import timber.log.Timber

abstract class BaseFragment<in V : View, P : Presenter<V>> : DaggerFragment(), View {
    private val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)
    open lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("BaseFragment running onCreate()")

        var viewModel: BaseViewModel<V, P> = ViewModelProviders.of(this).get(BaseViewModel::class.java) as BaseViewModel<V, P>
        if (viewModel.presenter == null) {
            viewModel.presenter = presenter
            Timber.d("Setting Presenter on BaseFragment.")
        }

        presenter = viewModel.presenter as P
        presenter.attach(this as V)
        lifecycle.addObserver(presenter)
    }

    override fun onViewCreated(view: android.view.View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.init()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry
}