package io.marlon.cleanarchitecture.data.objectbox

import io.marlon.cleanarchitecture.domain.datasource.RepoDataSource
import io.marlon.cleanarchitecture.domain.model.Repo
import io.marlon.cleanarchitecture.domain.model.Repo_
import io.marlon.cleanarchitecture.internal.di.qualifier.ObjectBox
import io.objectbox.Box
import io.objectbox.BoxStore
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for managing repositories in ObjectBox.
 */
@ObjectBox
@Singleton
class RepoObjectBoxDataSource @Inject constructor(boxStore: BoxStore) : RepoDataSource {
    var box: Box<Repo> = boxStore.boxFor(Repo::class.java)

    override fun getRepos(username: String): Flowable<List<Repo>> = Flowable.just(box.query().order(Repo_.name).build().find())

    override fun save(list: List<Repo>) {
        box.put(list)
    }

}