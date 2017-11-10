package io.marlon.cleanarchitecture.ui.mvp.user

import android.content.Context
import android.os.Bundle
import android.widget.ProgressBar
import dagger.android.support.DaggerAppCompatActivity
import io.marlon.cleanarchitecture.R
import io.marlon.cleanarchitecture.domain.model.User
import kotlinx.android.synthetic.main.activity_user_details.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast
import timber.log.Timber
import javax.inject.Inject

class UserActivity : DaggerAppCompatActivity(), UserContract.View {
    override val progressBar: ProgressBar by lazy { this.pBar }
    override val context: Context = this

    @Inject
    lateinit var presenter: UserContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        presenter.attach(this)
        presenter.bootstrap()

        btnLoad.onClick {
            Timber.d("Load User Click")
            presenter.loadUser(etLogin.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun showError(message: String) {
        toast(message)
    }

    override fun showUser(user: User) {
        etName.setText(user.name)
        etLogin.setText(user.login)
    }

}
