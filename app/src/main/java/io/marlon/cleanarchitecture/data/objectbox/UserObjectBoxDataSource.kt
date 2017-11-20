package io.marlon.cleanarchitecture.data.objectbox

import io.marlon.cleanarchitecture.domain.datasource.UserDataSource
import io.marlon.cleanarchitecture.domain.model.User
import io.marlon.cleanarchitecture.domain.model.User_
import io.marlon.cleanarchitecture.internal.di.qualifier.ObjectBox
import io.objectbox.Box
import io.objectbox.BoxStore
import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for managing users in ObjectBox.
 */
@ObjectBox
@Singleton
class UserObjectBoxDataSource @Inject constructor(boxStore: BoxStore) : UserDataSource {
    var box: Box<User> = boxStore.boxFor(User::class.java)

    override fun save(user: User): Single<User> {
        box.put(user)
        return Single.just(user)
    }

    override fun find(username: String): Flowable<User> {
        return if (box.query().equal(User_.login, username).build().count() == 1L) {
            Timber.d("Found User in ObjectBox")
            Flowable.just(box.query().build().find().first())
        } else {
            Timber.d("User not found in ObjectBox")
            Flowable.empty()
        }
    }

    override fun login(login: String?, password: String?): Single<String> {
        throw UnsupportedOperationException()
    }

}