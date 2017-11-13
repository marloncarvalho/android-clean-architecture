package io.marlon.cleanarchitecture.domain.usecase

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class UseCase<out Type, in Params> where Type : Any {
    internal val disposables = CompositeDisposable()

    fun clear() = disposables.clear()

    abstract fun build(params: Params?): Type

    abstract class RxSingle<T, in P> : UseCase<Single<T>, P>() {

        fun execute(observer: UseCaseObserver.RxSingle<T>, params: P? = null) =
                disposables.add(build(params)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(observer))

        fun execute(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit, params: P? = null) =
                disposables.add(build(params)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(onSuccess, onError))
    }

    abstract class RxObservable<T, in P> : UseCase<Observable<T>, P>() {
        fun execute(observer: UseCaseObserver.RxObservable<T>, params: P? = null) =
                disposables.add(build(params)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(observer))

        fun execute(onNext: (T) -> Unit, onError: (Throwable) -> Unit, params: P? = null) {
            disposables.add(build(params)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(onNext, onError))
        }
    }

    abstract class RxFlowable<T, in P> : UseCase<Flowable<T>, P>() {
        fun execute(subscriber: UseCaseObserver.RxFlowable<T>, params: P? = null) =
                disposables.add(build(params)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(subscriber))

        fun execute(onNext: (T) -> Unit, onError: (Throwable) -> Unit, params: P? = null) {
            disposables.add(build(params)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(onNext, onError))
        }
    }

    abstract class RxCompletable<in P> : UseCase<Completable, P>() {
        fun execute(params: P? = null) = execute({}, params)

        fun execute(onComplete: () -> Unit = {}, params: P? = null) =
                disposables.add(build(params)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(onComplete))
    }

}