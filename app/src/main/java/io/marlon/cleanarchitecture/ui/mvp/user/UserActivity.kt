package io.marlon.cleanarchitecture.ui.mvp.user

import android.os.Bundle
import android.widget.ProgressBar
import io.marlon.cleanarchitecture.R
import io.marlon.cleanarchitecture.domain.model.User
import io.marlon.cleanarchitecture.ui.mvp.BaseActivity
import kotlinx.android.synthetic.main.activity_user_details.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast
import timber.log.Timber
import javax.inject.Inject

class UserActivity : BaseActivity<UserContract.View, UserContract.Presenter>(), UserContract.View {

    @Inject
    override lateinit var presenter: UserContract.Presenter
    override val progressBar: ProgressBar by lazy { this.pBar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        btnLoad.onClick {
            Timber.d("Load User Click")
            presenter.loadUser(etLogin.text.toString())
        }
    }

    override fun showError(message: String) {
        toast(message)
    }

    override fun showUser(user: User) {
        etName.setText(user.name)
        etLogin.setText(user.login)
    }

}
