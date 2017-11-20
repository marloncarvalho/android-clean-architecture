package io.marlon.cleanarchitecture.ui.mvp.login

import android.os.Bundle
import android.widget.ProgressBar
import dagger.android.support.DaggerAppCompatActivity
import io.marlon.cleanarchitecture.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity(), LoginContract.View {
    override val progressBar: ProgressBar by lazy { this.pBar }

    @Inject
    lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter.attach(this)
        presenter.init()

        btnSignIn.onClick {
            presenter.login(etLogin.text.toString(), etPassword.text.toString())
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun showError(message: String) {
        toast(message)
    }

}