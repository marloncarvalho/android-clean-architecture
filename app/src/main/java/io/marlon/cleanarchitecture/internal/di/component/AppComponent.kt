package io.marlon.cleanarchitecture.internal.di.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import io.marlon.cleanarchitecture.App
import io.marlon.cleanarchitecture.internal.di.module.*
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(
                AndroidSupportInjectionModule::class,
                AppModule::class,
                AndroidBindings::class,
                DataSourceModule::class,
                PresenterModule::class,
                ActivityModule::class,
                APIModule::class

        )
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    fun inject(application: App)
}