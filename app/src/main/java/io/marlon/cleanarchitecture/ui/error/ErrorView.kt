package io.marlon.cleanarchitecture.ui.error

import android.content.Context
import android.widget.Toast

interface ErrorView {
    val context: Context

    fun showError(message: String)

    fun showStrangeErrorMessage() {
        Toast
                .makeText(
                        context,
                        "Ooops! Something really strange happened and we don't know what it is. Try again later!",
                        Toast.LENGTH_LONG)
                .show()
    }
}