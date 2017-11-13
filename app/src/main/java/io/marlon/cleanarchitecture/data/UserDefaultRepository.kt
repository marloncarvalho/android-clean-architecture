package io.marlon.cleanarchitecture.data

import io.marlon.cleanarchitecture.data.database.UserObjectBoxRepository
import io.marlon.cleanarchitecture.data.remote.UserRemoteRepository
import io.marlon.cleanarchitecture.domain.model.User
import io.marlon.cleanarchitecture.domain.repository.UserRepository
import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class UserDefaultRepository @Inject constructor(
        private val dbRepository: UserObjectBoxRepository,
        private val remoteRepository: UserRemoteRepository) : UserRepository {

    override fun login(login: String?, password: String?): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun save(user: User): Single<User> {
        return dbRepository.save(user)
    }

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
        var db = dbRepository.find(username)
        var remote = remoteRepository.find(username).doOnNext { user ->
            Timber.d("Saving User to Local Repository")
            dbRepository.save(user)
        }

        return Flowable.concat(db, remote)
    }

}