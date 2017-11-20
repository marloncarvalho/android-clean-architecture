package io.marlon.cleanarchitecture.ui.mvp

import android.content.Context
import io.marlon.cleanarchitecture.R
import io.marlon.cleanarchitecture.data.remote.exception.NetworkError
import io.marlon.cleanarchitecture.data.remote.exception.NetworkUnavailableException
import io.marlon.cleanarchitecture.domain.exception.DomainException
import javax.inject.Inject

/**
 * Handles common errors, like Network issues and domain exceptions.
 */
class ViewErrorHandler @Inject constructor(val context: Context) {

    fun handle(view: ErrorView, throwable: Throwable): Boolean {
        var result = false

        when (throwable) {
            is NetworkError -> {
                view.showError(context.getString(R.string.generic_error_network_error))
                result = true
            }
            is NetworkUnavailableException -> {
                view.showError(context.getString(R.string.generic_error_no_internet))
                result = true
            }
            !is DomainException -> {
                view.showError(context.getString(R.string.generic_error_unknown))
                result = true
            }
        }

        return result
    }

}