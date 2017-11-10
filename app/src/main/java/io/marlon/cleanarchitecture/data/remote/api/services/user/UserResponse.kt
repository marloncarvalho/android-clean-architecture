package io.marlon.cleanarchitecture.data.remote.api.services.user

data class UserResponse(
        var id: Long,
        var name: String? = "",
        var login: String? = "",
        var avatarUrl: String? = "",
        var url: String? = "",
        var bio: String? = "",
        var location: String? = "",
        var company: String? = "",
        var blog: String? = "",
        var countRepositories: Int? = 0,
        var countGists: Int? = 0
)