package io.marlon.cleanarchitecture

import android.app.Activity
import android.support.multidex.MultiDexApplication
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.marlon.cleanarchitecture.internal.di.component.AppComponent
import io.marlon.cleanarchitecture.internal.di.component.DaggerAppComponent
import io.marlon.cleanarchitecture.internal.di.module.AppModule
import timber.log.Timber
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject

open class App : MultiDexApplication(), HasActivityInjector {
    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        LeakCanary.install(this)

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setFontAttrId(R.attr.fontPath)
                .build())
    }

}