package io.marlon.cleanarchitecture.internal.di.module

import dagger.Module
import dagger.Provides
import io.marlon.cleanarchitecture.data.objectbox.RepoObjectBoxDataSource
import io.marlon.cleanarchitecture.data.objectbox.UserObjectBoxDataSource
import io.marlon.cleanarchitecture.data.remote.RepoRemoteDataSource
import io.marlon.cleanarchitecture.data.remote.UserRemoteDataSource
import io.marlon.cleanarchitecture.domain.datasource.RepoDataSource
import io.marlon.cleanarchitecture.domain.datasource.UserDataSource
import io.marlon.cleanarchitecture.internal.di.qualifier.ObjectBox
import io.marlon.cleanarchitecture.internal.di.qualifier.Remote
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Provides
    @Singleton
    @ObjectBox
    fun provideUserObjectBox(ds: UserObjectBoxDataSource): UserDataSource = ds

    @Provides
    @Singleton
    @Remote
    fun provideUserRemote(ds: UserRemoteDataSource): UserDataSource = ds

    @Provides
    @Singleton
    @ObjectBox
    fun provideRepoObjectBox(ds: RepoObjectBoxDataSource): RepoDataSource = ds

    @Provides
    @Singleton
    @Remote
    fun provideRepoRemote(ds: RepoRemoteDataSource): RepoDataSource = ds
}