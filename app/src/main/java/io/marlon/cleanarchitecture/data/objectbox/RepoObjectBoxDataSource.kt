package io.marlon.cleanarchitecture.data.objectbox

import io.marlon.cleanarchitecture.domain.datasource.RepoDataSource
import io.marlon.cleanarchitecture.domain.model.Repo
import io.marlon.cleanarchitecture.domain.model.Repo_
import io.marlon.cleanarchitecture.internal.di.qualifier.ObjectBox
import io.objectbox.Box
import io.objectbox.BoxStore
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for managing repositories in ObjectBox.
 */
@ObjectBox
@Singleton
class RepoObjectBoxDataSource @Inject constructor(boxStore: BoxStore) : RepoDataSource {
    var box: Box<Repo> = boxStore.boxFor(Repo::class.java)

    override fun getRepos(username: String): Observable<List<Repo>> {
        val result = box.query().order(Repo_.name).build().find()
        Timber.d("Loading repositories from ObjectBox -> ${result.count()}")

        return Observable.just(result)
    }

    override fun save(list: List<Repo>) {
        box.put(list)
    }

}