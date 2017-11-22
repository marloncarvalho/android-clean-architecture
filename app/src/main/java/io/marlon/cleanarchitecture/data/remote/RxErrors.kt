package io.marlon.cleanarchitecture.data.remote

import io.marlon.cleanarchitecture.data.remote.exception.NetworkError
import io.marlon.cleanarchitecture.data.remote.exception.NetworkUnavailableException
import io.marlon.cleanarchitecture.data.remote.exception.ResourceNotFoundException
import io.reactivex.*
import io.reactivex.functions.Function
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

/**
 * Handles common HTTP Exceptions, throwing a more meaningful exception.
 * It handles the errors below:
 * 1. IOException when there's no internet connection.
 * 2. Resource not found when http status code is 404.
 * 3. Server errors when http status code is equals or greater than 500.
 *
 * TODO We need to refactor it. Too much repetitions here.
 */
class RxErrors {

    companion object {

        fun handle(completable: Completable): Completable {
            return completable.onErrorResumeNext({ throwable ->
                val result = handleCommonErrors(throwable)
                if (result != null) {
                    Completable.error(result)
                } else {
                    completable
                }
            }).onErrorResumeNext({ throwable ->
                if (throwable is HttpException && throwable.code() == 401) {
                    Timber.d("Not authorized to call remote service.")
                    retry()
                    Completable.error(throwable)
                } else {
                    completable
                }
            })
        }

        fun <T> handle(maybe: Maybe<T>): Maybe<T> {
            return maybe.onErrorResumeNext(Function { throwable ->
                val result = handleCommonErrors(throwable)
                if (result != null) {
                    Maybe.error(result)
                } else if (throwable is HttpException && throwable.code() == 401) {
                    Timber.d("Not authorized to call remote service.")
                    retry()
                    Maybe.error(throwable)
                } else {
                    maybe
                }
            })
        }

        fun <T> handle(flowable: Flowable<T>): Flowable<T> {
            return flowable.onErrorResumeNext(Function { throwable ->
                val result = handleCommonErrors(throwable)
                if (result != null) {
                    Flowable.error(result)
                } else if (throwable is HttpException && throwable.code() == 401) {
                    Timber.d("Not authorized to call remote service.")
                    retry()
                    Flowable.error(throwable)
                } else {
                    flowable
                }
            })
        }

        fun <T> handle(observable: Observable<T>): Observable<T> {
            return observable.onErrorResumeNext(Function { throwable ->
                val result = handleCommonErrors(throwable)
                if (result != null) {
                    Observable.error(result)
                } else if (throwable is HttpException && throwable.code() == 401) {
                    Timber.d("Not authorized to call remote service.")
                    retry()
                    Observable.error(throwable)
                } else {
                    observable
                }
            })
        }

        fun <T> handle(single: Single<T>): Single<T> {
            return single.onErrorResumeNext({ throwable ->
                val result = handleCommonErrors(throwable)
                if (result != null) {
                    Single.error(result)
                } else if (throwable is HttpException && throwable.code() == 401) {
                    Timber.d("Not authorized to call remote service.")
                    retry()
                    Single.error(throwable)
                } else {
                    single
                }
            })
        }

        private fun handleHttpException(exception: HttpException): Throwable {
            var result: Throwable = exception

            if (exception.code() == 404) {
                Timber.d("Received Status Code 404")
                result = ResourceNotFoundException(exception.message())
            } else if (exception.code() >= 500) {
                Timber.d("Received Status Code 500")
                result = NetworkError(exception.message())
            }

            return result
        }

        private fun handleOtherExceptions(exception: Throwable): Throwable {
            var result: Throwable = exception

            if (exception is IOException) {
                Timber.d("IOException. No Internet connection")
                result = NetworkUnavailableException("No Internet Connection")
            }

            return result
        }

        private fun handleCommonErrors(throwable: Throwable): Throwable? {
            Timber.d("Error calling remote service.")

            return when (throwable) {
                is HttpException -> handleHttpException(throwable)
                is IOException -> handleOtherExceptions(throwable)
                else -> null
            }
        }

        private fun retry() {

        }
    }

}