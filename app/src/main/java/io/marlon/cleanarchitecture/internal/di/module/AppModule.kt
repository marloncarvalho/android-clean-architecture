package io.marlon.cleanarchitecture.internal.di.module

import android.content.Context
import br.gov.serpro.sne.domain.executor.JobExecutor
import br.gov.serpro.sne.domain.executor.PostExecutionThread
import br.gov.serpro.sne.domain.executor.ThreadExecutor
import br.gov.serpro.sne.domain.executor.UIThread
import dagger.Module
import dagger.Provides
import io.marlon.cleanarchitecture.App
import io.marlon.cleanarchitecture.domain.model.MyObjectBox
import io.objectbox.BoxStore
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    fun provideApplication(): App = app

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = app

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread = uiThread

    @Provides
    @Singleton
    fun provideBoxStore(context: Context): BoxStore = MyObjectBox.builder().androidContext(context).build()

}