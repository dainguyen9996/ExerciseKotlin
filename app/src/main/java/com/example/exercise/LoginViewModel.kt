package com.example.exercise

import androidx.lifecycle.ViewModel
import com.example.exercise.model.Staff
import io.realm.Realm
import java.util.*

class LoginViewModel : ViewModel() {
    //создаем экземпляр Realm с дефолтной конфигурацией
    private var realm: Realm = Realm.getDefaultInstance()

    //добавляем данные в Realm
    fun addAdmin(userName: String, pass: String): String {
        //выполняем транзакцию для добавления данных в Realm
        var id = ""
        realm.executeTransaction { realm ->
            realm.where(Staff::class.java).findAll()
                .firstOrNull { it.email == "admin@gm.vn" }?.let { staff ->
                    id = staff.id
                } ?: kotlin.run {
                val note =
                    realm.createObject(Staff::class.java, UUID.randomUUID().toString()).apply {
                        name = "Admin"
                        password = pass
                        email = userName
                        department = "IT"
                        address = "hn"
                    }
                realm.insertOrUpdate(note)
                id = UUID.randomUUID().toString()
            }

        }
        return id
    }

    fun login(userName: String, pass: String): Boolean {
        //выполняем транзакцию для добавления данных в Realm
        var success = false
        realm.executeTransaction { realm ->
            realm.where(Staff::class.java).findAll()
                .firstOrNull { it.email == userName }?.let { staff ->
                    staff.password == pass
                    success = true
                }
            //создаем объект, который должен быть помещен в БД

        }
        return success
    }

    fun getUserId(userName: String): String {
        var id = ""
        realm.executeTransaction { realm ->
            realm.where(Staff::class.java).findAll()
                .firstOrNull { it.email == userName }?.let { staff ->
                    id = staff.id
                }
            //создаем объект, который должен быть помещен в БД
        }

        return id
    }

}