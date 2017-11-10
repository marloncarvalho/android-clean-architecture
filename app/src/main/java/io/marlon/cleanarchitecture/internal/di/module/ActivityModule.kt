package io.marlon.cleanarchitecture.internal.di.module

import br.gov.serpro.sne.internal.di.scopes.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.marlon.cleanarchitecture.ui.mvp.user.UserActivity

@Module
abstract class ActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun mainActivity(): UserActivity

}