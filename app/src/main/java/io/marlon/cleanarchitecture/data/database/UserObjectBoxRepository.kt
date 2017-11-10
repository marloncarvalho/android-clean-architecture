package io.marlon.cleanarchitecture.data.database

import io.marlon.cleanarchitecture.domain.model.User
import io.marlon.cleanarchitecture.domain.repository.UserRepository
import io.objectbox.Box
import io.objectbox.BoxStore
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class UserObjectBoxRepository @Inject constructor(boxStore: BoxStore) : UserRepository, BaseDBRepository() {
    var box: Box<User> = boxStore.boxFor(User::class.java)

    override fun save(user: User): Single<User> {
        box.put(user)
        return Single.just(user)
    }

    override fun find(username: String): Flowable<User> {
        return if (box.query().build().count() == 1L) {
            Timber.d("Found User in ObjectBox")
            Flowable.just(box.query().build().find().first())
        } else {
            Timber.d("User not found in ObjectBox")
            Flowable.empty()
        }
    }

}