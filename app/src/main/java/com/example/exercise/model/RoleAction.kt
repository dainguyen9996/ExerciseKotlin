package com.example.exercise.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RoleAction : RealmObject {
    @PrimaryKey()
    var id: String? = null
    var creatStaff: Boolean? = null
    var edittStaff: Boolean? = null
    var delStaff: Boolean? = null
    var creatRole: Boolean? = null
    var editRole: Boolean? = null
    var delRole: Boolean? = null
    var creatAt: String? = null
    var creatBy: String? = null

    constructor(
        id: String?,
        createStaff: Boolean?,
        edittStaff: Boolean?,
        delStaff: Boolean?,
        creatRole: Boolean?,
        editRole: Boolean?,
        delRole: Boolean?,
        creatAt: String?,
        creatBy: String?
    ) : super() {
        this.id = id
        this.creatStaff = createStaff
        this.edittStaff = edittStaff
        this.delStaff = delStaff
        this.creatRole = creatRole
        this.editRole = editRole
        this.delRole = delRole
        this.creatAt = creatAt
        this.creatBy = creatBy

    }

    constructor() {}
}