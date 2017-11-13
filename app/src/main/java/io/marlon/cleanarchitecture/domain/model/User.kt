package io.marlon.cleanarchitecture.domain.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
class User() {

    @Id(assignable = true)
    var id: Long? = 0
    var name: String? = null
    var login: String? = null
    var password: String? = null

    constructor(id: Long, name: String? = "", login: String? = "") : this() {
        this.id = id
        this.name = name
        this.login = login
    }

}