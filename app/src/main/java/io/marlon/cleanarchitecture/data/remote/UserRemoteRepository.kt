package io.marlon.cleanarchitecture.data.remote

import io.marlon.cleanarchitecture.data.converter.UserRemoteConverter
import io.marlon.cleanarchitecture.data.remote.api.services.user.UserAPI
import io.marlon.cleanarchitecture.domain.model.User
import io.marlon.cleanarchitecture.domain.repository.UserRepository
import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class UserRemoteRepository @Inject constructor(
        private val converter: UserRemoteConverter,
        private val userAPI: UserAPI) : UserRepository {

    override fun save(user: User): Single<User> {
        throw NotImplementedError()
    }

    override fun find(username: String): Flowable<User> {
        return RxErrors.handle(userAPI.getUser(username).map {
            Timber.d("Found User. Converting it to Model. ${it.id} -> ${it.name}")
            converter.convert(it)
        }).toFlowable()
    }

    override fun login(login: String?, password: String?): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}