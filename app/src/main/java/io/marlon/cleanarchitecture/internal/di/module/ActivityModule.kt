package io.marlon.cleanarchitecture.internal.di.module

import br.gov.serpro.sne.internal.di.scopes.ActivityScoped
import br.gov.serpro.sne.internal.di.scopes.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.marlon.cleanarchitecture.ui.mvp.login.LoginActivity
import io.marlon.cleanarchitecture.ui.mvp.main.MainActivity
import io.marlon.cleanarchitecture.ui.mvp.repos.ReposFragment
import io.marlon.cleanarchitecture.ui.mvp.user.UserActivity

@Module
abstract class ActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun reposFragment(): ReposFragment

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun userActivity(): UserActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun loginActivity(): LoginActivity

}