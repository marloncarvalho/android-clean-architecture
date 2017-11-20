package io.marlon.cleanarchitecture.domain.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Repo(
        @Id(assignable = true)
        var id: Long,
        var name: String
)