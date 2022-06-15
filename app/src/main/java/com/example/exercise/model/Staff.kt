package com.example.exercise.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Staff : RealmObject {
    @PrimaryKey
    var id: String? = null
    var name: String? = null
    var email: String? = null
    var password: String? = null
    var createAt: String? = null
    var department: String? = "kt"
    var address: String? = null
    var createBy: String? = null
    var role: Role? = null

    constructor(
        id: String?,
        name: String?,
        email: String?,
        password: String?,
        createAt: String?,
        department: String?,
        address: String?,
        createBy: String?,
        roleName: Role?,
    ) : super() {
        this.id = id
        this.name = name
        this.email = email
        this.password = password
        this.createAt = createAt
        this.department = department
        this.address = address
        this.createBy = createBy
        this.role = roleName
    }

    constructor()
}
//
//@RealmClass
//open class Staff: RealmModel {
//    @PrimaryKey
//    var id : String = "123"
//
//    @Required
//    var name: String? = ""
//
//    @Required
//    var password: String? = ""
//
//    @Required
//    var email: String? = ""
//
//    @Required
//    var createAt: String? = ""
//
//    @Required
//    var department: String? = "it"
//
//    @Required
//    var address: String? = ""
//
//    @Required
//    var createBy: String? = "123"
//
//    @Required
//    var roleName: String = "staff"
//}

