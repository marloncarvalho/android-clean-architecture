package io.marlon.cleanarchitecture.data.remote

import android.util.Base64
import io.marlon.cleanarchitecture.data.converter.UserRemoteConverter
import io.marlon.cleanarchitecture.data.remote.api.services.user.UserAPI
import io.marlon.cleanarchitecture.domain.datasource.UserDataSource
import io.marlon.cleanarchitecture.domain.model.User
import io.marlon.cleanarchitecture.internal.di.qualifier.Remote
import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Remote
@Singleton
class UserRemoteDataSource @Inject constructor(
        private val converter: UserRemoteConverter,
        private val userAPI: UserAPI) : UserDataSource {

    override fun save(user: User): Single<User> {
        throw UnsupportedOperationException()
    }

    override fun find(username: String): Flowable<User> {
        return RxErrors.handle(userAPI.getUser(username).map {
            Timber.d("Found User. Converting it to Model. ${it.id} -> ${it.name}")
            converter.convert(it)
        }).toFlowable()
    }

    override fun login(login: String?, password: String?): Single<String> {
        var base64 = Base64.encode("$login:$password".toByteArray(Charsets.UTF_8), Base64.DEFAULT).toString()
        return userAPI.login("Basic $base64")
    }

}