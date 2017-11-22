package io.marlon.cleanarchitecture.data

import io.marlon.cleanarchitecture.domain.datasource.RepoDataSource
import io.marlon.cleanarchitecture.domain.model.Repo
import io.marlon.cleanarchitecture.internal.di.qualifier.ObjectBox
import io.marlon.cleanarchitecture.internal.di.qualifier.Remote
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class RepoRepository @Inject constructor(
        @ObjectBox private val objectBox: RepoDataSource,
        @Remote private val remote: RepoDataSource
) : RepoDataSource {

    override fun save(list: List<Repo>) {
        objectBox.save(list)
    }

    override fun getRepos(username: String): Observable<List<Repo>> {
        val local = objectBox.getRepos(username).doOnNext {
            Timber.d("DoOnNext ObjectBox Got Repos: ${it.count()}")
        }.subscribeOn(Schedulers.io())

        val remote = remote.getRepos(username).doOnNext { repos ->
            Timber.d("Saving Repos to ObjectBox.")
            save(repos)
        }.subscribeOn(Schedulers.io())

        return Observable.concat(local, remote)
    }

}