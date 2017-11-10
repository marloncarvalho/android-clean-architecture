package io.marlon.cleanarchitecture.internal.di.module

import dagger.Module
import dagger.Provides
import io.marlon.cleanarchitecture.data.UserDefaultRepository
import io.marlon.cleanarchitecture.domain.repository.UserRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(repository: UserDefaultRepository): UserRepository = repository

}