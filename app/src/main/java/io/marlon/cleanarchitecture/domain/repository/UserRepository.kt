package io.marlon.cleanarchitecture.domain.repository

import io.marlon.cleanarchitecture.domain.model.User
import io.reactivex.Flowable
import io.reactivex.Single

interface UserRepository {
    fun find(username: String): Flowable<User>
    fun save(user: User): Single<User>
    fun login(login: String?, password: String?): Single<User>
}