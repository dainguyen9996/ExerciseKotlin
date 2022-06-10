package com.example.exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercise.model.Staff

import io.realm.Realm
import io.realm.kotlin.deleteFromRealm
import java.util.*

class MainViewModel : ViewModel() {
    //создаем экземпляр Realm с дефолтной конфигурацией
    private var realm: Realm = Realm.getDefaultInstance()

    val allNotes: LiveData<MutableList<Staff>>
    get() = getAllNotes()
//
//    //добавляем данные в Realm
    fun addStaff(nameValue: String, userName: String, pass: String, dept: String, addressValue: String) {
        //выполняем транзакцию для добавления данных в Realm
        realm.executeTransaction { realm ->
            //создаем объект, который должен быть помещен в БД
            val note = realm.createObject(Staff::class.java, UUID.randomUUID().toString()).apply {
                name = nameValue
                password = pass
                email = userName
                department = dept
                address = addressValue
            }
            realm.insertOrUpdate(note)
        }
    }

    fun getInfo(userId: String) : Staff? {
        //выполняем транзакцию для добавления данных в Realm
        var staffValue : Staff? = null
        realm.executeTransaction { realm ->
            realm.where(Staff::class.java).findAll()
                .firstOrNull { it.id == userId }?.let { staff ->
                    staffValue = staff
                }
            //создаем объект, который должен быть помещен в БД
        }
        return staffValue
    }
//
    private fun getAllNotes(): MutableLiveData<MutableList<Staff>> {
        val list = MutableLiveData<MutableList<Staff>>()

        /*
            Берем данные из БД и помещаем их в RealmResult. Этот
            результат может быть конвертирован в список
         */
        val notes = realm.where(Staff::class.java).findAll().filter { it.roleName == "staff" } as MutableList ?: null
        list.value = notes?.subList(0, notes.size)
        return list
    }
//
//    /*
//        обновление данных похоже на вставку данных, только на этот раз
//        мы запрашиваем данные с соответствующим идентификатором данных,
//        которые нам нужно обновить.
//     */
    fun updateNote(id: String, name: String, pass: String, email: String, department :String, address: String ) {
        val target = realm.where(Staff::class.java)
            .equalTo("id", id)
            .findFirst()

        realm.executeTransaction {
            target?.name = name
            target?.password = pass
            target?.email = email
            target?.address = address
            target?.department = department
            realm.insertOrUpdate(target)
        }
    }
//
//    //удаление одиночного item из объекта realm
    fun deleteNote(id: String) {
        val notes = realm.where(Staff::class.java)
            .equalTo("id", id)
            .findFirst()

        realm.executeTransaction {
            notes?.deleteFromRealm()
        }
    }
//
//    //удаление всех данных из объекта Realm
//    fun deleteAllNotes() {
//        realm.executeTransaction {
//            it.delete(Note::class.java)
//        }
//    }
}