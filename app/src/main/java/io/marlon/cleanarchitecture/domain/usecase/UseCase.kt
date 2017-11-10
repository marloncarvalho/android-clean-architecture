package io.marlon.cleanarchitecture.domain.usecase

import br.gov.serpro.sne.domain.executor.PostExecutionThread
import br.gov.serpro.sne.domain.executor.ThreadExecutor
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import timber.log.Timber

abstract class UseCase<T, in Params>(
        private val threadExecutor: ThreadExecutor,
        private val postExecutionThread: PostExecutionThread) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    abstract fun doRun(params: Params? = null): Flowable<T>

    open fun run(params: Params? = null, onError: (Throwable) -> Unit = {}, onNext: (T) -> Unit = {}, onComplete: () -> Unit = {}) {
        Timber.d("Running UseCase!")

        val observable = this.doRun(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler)

        Timber.d("Adding Subscriber to Composite Disposable")
        add(observable.subscribeWith(object : DisposableSubscriber<T>() {

            override fun onComplete() {
                Timber.d("Subscriber passing onComplete call to Lambda.")
                onComplete.invoke()
            }

            override fun onError(t: Throwable) {
                Timber.d("Subscriber passing onError call to Lambda.")
                onError.invoke(t)
            }

            override fun onNext(t: T) {
                Timber.d("Subscriber passing onNext call to Lambda.")
                onNext.invoke(t)
            }
        }))
    }

    open fun clear() {
        disposables.clear()
    }

    private fun add(disposable: Disposable) {
        disposables.add(disposable)
    }

}