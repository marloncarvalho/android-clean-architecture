package io.marlon.cleanarchitecture.domain.usecase.user

import br.gov.serpro.sne.domain.executor.PostExecutionThread
import br.gov.serpro.sne.domain.executor.ThreadExecutor
import io.marlon.cleanarchitecture.data.remote.exception.ResourceNotFoundException
import io.marlon.cleanarchitecture.domain.exception.ModelNotFound
import io.marlon.cleanarchitecture.domain.model.User
import io.marlon.cleanarchitecture.domain.repository.UserRepository
import io.marlon.cleanarchitecture.domain.usecase.UseCase
import io.reactivex.Flowable
import io.reactivex.functions.Function
import timber.log.Timber
import javax.inject.Inject

class GetUserDetails @Inject constructor(
        private val userRepository: UserRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread) : UseCase<User, String>(threadExecutor, postExecutionThread) {

    override fun doRun(username: String?): Flowable<User> {
        return userRepository.find(username!!).onErrorResumeNext(Function { throwable ->
            if (throwable is ResourceNotFoundException) {
                Timber.d("Resource Not Found. Converting it to Domain Exception.")
                Flowable.error(ModelNotFound("Model Not Found"))
            } else {
                Timber.d("Error found but UseCase can't handle it.")
                Flowable.error(throwable)
            }
        })
    }

}