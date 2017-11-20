package io.marlon.cleanarchitecture.data

import io.marlon.cleanarchitecture.data.objectbox.UserObjectBoxDataSource
import io.marlon.cleanarchitecture.data.remote.UserRemoteDataSource
import io.marlon.cleanarchitecture.domain.datasource.UserDataSource
import io.marlon.cleanarchitecture.domain.model.User
import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class UserRepository @Inject constructor(
        private val objectBoxDataSource: UserObjectBoxDataSource,
        private val remoteDataSource: UserRemoteDataSource) : UserDataSource {

    override fun login(login: String?, password: String?): Single<String> =
            remoteDataSource.login(login, password)

    override fun save(user: User): Single<User> = objectBoxDataSource.save(user)

    /**
     * We'll first try to retrieve the user from local storage.
     * If it's present locally, then we return it so it can be presented to the user immediately.
     *
     * After that, we'll get this user from our remote server and update it locally.
     * This new data we'll then be sent to the view layer.
     *
     * That's why we return a Flowable instead of a Maybe or Single.
     */
    override fun find(username: String): Flowable<User> {
        var db = objectBoxDataSource.find(username)
        var remote = remoteDataSource.find(username).doOnNext { user ->
            Timber.d("Saving User to ObjectBox.")
            objectBoxDataSource.save(user)
        }

        return Flowable.concat(db, remote)
    }

}