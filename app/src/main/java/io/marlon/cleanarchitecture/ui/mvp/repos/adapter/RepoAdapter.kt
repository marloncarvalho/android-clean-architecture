package io.marlon.cleanarchitecture.ui.mvp.repos.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.marlon.cleanarchitecture.R
import io.marlon.cleanarchitecture.domain.model.Repo
import io.marlon.cleanarchitecture.internal.ui.extension.inflate
import kotlinx.android.synthetic.main.adapter_item_repo.view.*

class RepoAdapter(private val repos: List<Repo>) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RepoViewHolder =
            RepoViewHolder(parent?.inflate(R.layout.adapter_item_repo)!!)

    override fun getItemCount(): Int = repos.count()

    override fun onBindViewHolder(holder: RepoViewHolder?, position: Int) {
        holder?.bind(repos[position])
    }

    /**
     * View Holder.
     */
    class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(repo: Repo) = with(itemView) {
            repoName.text = repo.name
        }

    }

}