package io.marlon.cleanarchitecture.ui.mvp

import android.view.View
import android.widget.ProgressBar

interface LoadingView {
    val progressBar: ProgressBar

    fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

}