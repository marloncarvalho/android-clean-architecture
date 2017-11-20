package io.marlon.cleanarchitecture.data

import io.marlon.cleanarchitecture.domain.datasource.RepoDataSource
import io.marlon.cleanarchitecture.domain.model.Repo
import io.marlon.cleanarchitecture.internal.di.qualifier.ObjectBox
import io.marlon.cleanarchitecture.internal.di.qualifier.Remote
import io.reactivex.Flowable
import timber.log.Timber
import javax.inject.Inject

class RepoRepository @Inject constructor(
        @ObjectBox private val objectBox: RepoDataSource,
        @Remote private val remote: RepoDataSource
) : RepoDataSource {

    override fun save(list: List<Repo>) {
        objectBox.save(list)
    }

    override fun getRepos(username: String): Flowable<List<Repo>> {
        val local = objectBox.getRepos(username)
        val remote = remote.getRepos(username).doOnNext { repos ->
            Timber.d("Saving Repos to ObjectBox.")
            save(repos)
        }

        return Flowable.concat(local, remote)
    }

}