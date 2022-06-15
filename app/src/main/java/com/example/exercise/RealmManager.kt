package com.example.exercise

import com.example.exercise.model.Role
import com.example.exercise.model.Staff
import io.realm.Realm
import io.realm.RealmResults

class RealmManager {
    private val TAG: String = RealmManager::class.java.simpleName

    private var realm: Realm = Realm.getDefaultInstance()

    // I create a companion object to use outside
    companion object {
        private var realmManager: RealmManager? = null
        val instance: RealmManager?
            get() {
                if (realmManager == null) {
                    realmManager = RealmManager()
                }
                return realmManager
            }
    }

    fun saveStaff(note: Staff) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(note)
        realm.commitTransaction()
    }

    fun saveAdmin(note: Staff) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(note)
        realm.commitTransaction()
    }

    fun loadStaffs(): ArrayList<Staff> {
        val notes: ArrayList<Staff> = ArrayList()

        val results: RealmResults<Staff> = realm.where(Staff::class.java).findAll()

        notes.addAll(results)
        return notes
    }

    fun saveRole(role: Role) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(role)
        realm.commitTransaction()
    }

    fun loadRole(): ArrayList<Role> {
        val notes: ArrayList<Role> = ArrayList()

        val results: RealmResults<Role> = realm.where(Role::class.java).findAll()

        notes.addAll(results)
        return notes
    }
}
