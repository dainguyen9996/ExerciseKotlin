package com.example.exercise

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        myAppInstance = this
        Realm.init(this)
//        val config = RealmConfiguration.Builder()
//            .name("Person.db")
//            .deleteRealmIfMigrationNeeded()
//            .build()

        val configuration = RealmConfiguration.Builder()
            .name("todo.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()

        Realm.setDefaultConfiguration(configuration)
    }

    companion object {

        private lateinit var myAppInstance: MyApplication

        fun get(): MyApplication {
            return myAppInstance
        }
    }
}