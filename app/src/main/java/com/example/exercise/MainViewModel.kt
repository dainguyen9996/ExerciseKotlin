package com.example.exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercise.model.Staff
import io.realm.Case
import io.realm.Realm
import io.realm.RealmQuery
import io.realm.RealmResults
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
        realm.executeTransaction { mRealm -> //Change the previous mRealm to the realm parameter object and it's fine
            //val realmResults: RealmResults<Staff> = (mRealm.where(Staff::class.java).findAll().filter { it.roleName == "staff" } as MutableList ?: null) as RealmResults<Staff>
            val notes = mRealm.where(Staff::class.java).findAll()
                .filter { it.roleName == "staff" } as MutableList
            list.value = notes.subList(0, notes.size)
        }

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

    //    //удаление одиночного item из объекта realm
    fun deleteNote(id: String) {
        try {
            val notes = realm.where(Staff::class.java)
                .equalTo("id", id)
                .findAll()

            realm.beginTransaction()
            notes.deleteAllFromRealm()
            realm.commitTransaction()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun searchStaff(key: String): List<Staff>? {
        return allNotes.value?.filter { it.name?.contains(key, true) != null }
    }

    fun searchStaffByName(key: String): MutableList<Staff> {
        val list: MutableList<Staff> = mutableListOf()
        try {
            val query: RealmQuery<Staff> = realm.where(Staff::class.java)
            query.contains("name", key, Case.INSENSITIVE)
            val result1: RealmResults<Staff> = query.findAll()
            list.addAll(result1)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

        return list
    }
//
//    //удаление всех данных из объекта Realm
//    fun deleteAllNotes() {
//        realm.executeTransaction {
//            it.delete(Note::class.java)
//        }
//    }
}