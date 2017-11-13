package io.marlon.cleanarchitecture.domain.usecase.login

import io.marlon.cleanarchitecture.domain.model.User
import io.marlon.cleanarchitecture.domain.repository.UserRepository
import io.marlon.cleanarchitecture.domain.usecase.UseCase
import io.reactivex.Single
import javax.inject.Inject

class Login @Inject constructor(
        private val userRepository: UserRepository) : UseCase.RxSingle<User, Login.Param>() {

    override fun build(login: Param?): Single<User> {
        return userRepository.login(login?.login, login?.password)
    }

    class Param() {
        lateinit var login: String
        lateinit var password: String

        constructor(login: String, password: String) : this() {
            this.login = login
            this.password = password
        }
    }

}