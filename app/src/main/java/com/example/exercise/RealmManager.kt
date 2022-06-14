package com.example.exercise

import com.example.exercise.model.Staff
import io.realm.Realm
import io.realm.RealmResults

class RealmManager {
    private val TAG: String = RealmManager::class.java.simpleName

    private var realm : Realm = Realm.getDefaultInstance()

    // I create a companion object to use outside
    companion object{
        private var realmManager : RealmManager? = null
        val instance : RealmManager?
            get() {
                if(realmManager == null){
                    realmManager = RealmManager()
                }
                return realmManager
            }
    }

    fun saveNote(note : Staff){
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(note)
        realm.commitTransaction()
    }

    fun saveAdmin(note : Staff){
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(note)
        realm.commitTransaction()
    }

    fun loadNotes() : ArrayList<Staff>{
        val notes : ArrayList<Staff> = ArrayList()

        val results : RealmResults<Staff> = realm.where(Staff::class.java).findAll()

        notes.addAll(results)
        return notes
    }
}
