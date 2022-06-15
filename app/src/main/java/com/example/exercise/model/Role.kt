package com.example.exercise.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Role : RealmObject {
    @PrimaryKey()
    var id: String? = null
    var name: String? = "staff"
    var description: String? = null
    var createAt: String? = null
    var createBy: String? = null
    var roleAction: RoleAction? = null

    constructor(
        id: String?,
        name: String?,
        description: String?,
        createAt: String?,
        createBy: String?,
        roleAction: RoleAction?,
    ) : super() {
        this.id = id
        this.name = name
        this.description = description
        this.createAt = createAt
        this.createBy = createBy
        this.roleAction = roleAction
    }

    constructor() {}
}