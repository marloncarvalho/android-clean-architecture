package io.marlon.cleanarchitecture.ui.mvp.main

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import io.marlon.cleanarchitecture.R
import io.marlon.cleanarchitecture.ui.mvp.repos.ReposFragment
import javax.inject.Inject

open class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var reposFragment: ReposFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, reposFragment)
                .commit()
    }

}