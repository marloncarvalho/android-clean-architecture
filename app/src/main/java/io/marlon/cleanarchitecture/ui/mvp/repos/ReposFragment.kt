package io.marlon.cleanarchitecture.ui.mvp.repos

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import io.marlon.cleanarchitecture.R
import io.marlon.cleanarchitecture.domain.model.Repo
import io.marlon.cleanarchitecture.ui.mvp.BaseFragment
import io.marlon.cleanarchitecture.ui.mvp.repos.adapter.RepoAdapter
import kotlinx.android.synthetic.main.activity_user_details.*
import kotlinx.android.synthetic.main.fragment_repos.view.*
import org.jetbrains.anko.support.v4.toast
import timber.log.Timber
import javax.inject.Inject

class ReposFragment @Inject constructor() : BaseFragment<ReposContract.View, ReposContract.Presenter>(), ReposContract.View {
    override val progressBar: ProgressBar by lazy { this.pBar }
    lateinit var listRepos: RecyclerView
    lateinit var refreshLayout: SwipeRefreshLayout

    @Inject
    override lateinit var presenter: ReposContract.Presenter

    override fun showRepos(repos: List<Repo>) {
        Timber.d("Showing repositories.")
        listRepos.adapter = RepoAdapter(repos)
        refreshLayout.isRefreshing = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_repos, container, false)
        listRepos = view.listRepos
        refreshLayout = view.refreshLayout

        listRepos.layoutManager = LinearLayoutManager(activity)
        refreshLayout.setOnRefreshListener {
            presenter.refreshRepos()
        }

        return view
    }

    override fun showError(message: String) {
        toast(message)
    }

}