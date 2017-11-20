package io.marlon.cleanarchitecture.data.converter

import io.marlon.cleanarchitecture.data.remote.api.services.repo.RepoResponse
import io.marlon.cleanarchitecture.domain.converter.Converter
import io.marlon.cleanarchitecture.domain.model.Repo
import javax.inject.Inject

class RepoRemoteConverter @Inject constructor(): Converter<RepoResponse, Repo> {
    override fun convert(input: RepoResponse): Repo = Repo(input.id, input.name!!)
    override fun convert(listInput: List<RepoResponse>): List<Repo> = listInput.map { convert(it) }
    override fun revert(output: Repo): RepoResponse = RepoResponse(output.id, output.name)
    override fun revert(listOutput: List<Repo>): List<RepoResponse> = listOutput.map { revert(it) }
}