package com.example.exercise.model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

//class Staff : RealmObject {
//    var id: Int = 0
//    var firstName: String? = ""
//    var lastName: String? = ""
//    var email: String? = ""
//    var password: String? = ""
//    var department: String? = ""
//    var createAt: String? = ""
//    var createdBy: Int = 0
//}

@RealmClass
open class Staff: RealmModel {
    @PrimaryKey
    var id : String = "123"

    @Required
    var name: String? = ""

    @Required
    var password: String? = ""

    @Required
    var email: String? = ""

    @Required
    var createAt: String? = ""

    @Required
    var department: String? = ""

    @Required
    var address: String? = ""

    @Required
    var createBy: String? = "123"
}

