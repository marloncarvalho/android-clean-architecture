package io.marlon.cleanarchitecture.ui.mvp

import io.marlon.cleanarchitecture.data.remote.exception.NetworkError
import io.marlon.cleanarchitecture.data.remote.exception.NetworkUnavailable
import io.marlon.cleanarchitecture.domain.exception.DomainException
import javax.inject.Inject

/**
 * Handles common errors, like Network issues and domain exceptions.
 */
class ViewErrorHandler @Inject constructor() {

    fun handle(view: ErrorView, throwable: Throwable): Boolean {
        var result = false

        when (throwable) {
            is NetworkError -> {
                view.showError("Our servers are upset and don't want to talk with us right now. Please, try again later.")
                result = true
            }
            is NetworkUnavailable -> {
                view.showError("Oops! Are you sure your internet connection is okay?")
                result = true
            }
            !is DomainException -> {
                view.showError("Ooops! Something really strange happened and we don't know what it is. Try again later!")
                result = true
            }
        }

        return result
    }

}