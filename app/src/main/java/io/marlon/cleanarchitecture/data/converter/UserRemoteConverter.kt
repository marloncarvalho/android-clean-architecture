package io.marlon.cleanarchitecture.data.converter

import io.marlon.cleanarchitecture.data.remote.api.services.user.UserResponse
import io.marlon.cleanarchitecture.domain.converter.Converter
import io.marlon.cleanarchitecture.domain.model.User
import timber.log.Timber
import javax.inject.Inject

class UserRemoteConverter @Inject constructor() : Converter<UserResponse, User> {

    override fun revert(output: User): UserResponse {
        return UserResponse(output.id!!, output.name, output.login)
    }

    override fun revert(listOutput: List<User>): List<UserResponse> {
        return listOutput.map { revert(it) }
    }

    override fun convert(listInput: List<UserResponse>): List<User> {
        return listInput.map { convert(it) }
    }

    override fun convert(input: UserResponse): User {
        Timber.d("Converter: ${input.id} -> ${input.name}")
        return User(input.id, input.name, input.login)
    }

}