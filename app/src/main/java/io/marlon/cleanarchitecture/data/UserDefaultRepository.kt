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

    override fun save(user: User): Single<User> {
        return dbRepository.save(user)
    }

    override fun find(username: String): Flowable<User> {
        var db = dbRepository.find(username)
        var remote = remoteRepository.find(username).doOnNext { user ->
            Timber.d("Saving User to Local Repository")
            dbRepository.save(user)
        }

        return Flowable.concat(db, remote)
    }

}