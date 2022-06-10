package com.example.exercise.model

import android.content.SharedPreferences
import com.example.exercise.pref.AppContext
import java.util.*
import com.example.exercise.pref.PreferenceHelper

/**
 * @author by hunghoang on 7/21/18.
 */
class AppPrefs {
    private val pref: SharedPreferences = PreferenceHelper.newPrefs(AppContext, SHARE_PREF_NAME)

    companion object {
        private const val SHARE_PREF_NAME = "App.Pref"
        private const val KEY_AUTH = "key.auth"
        private const val KEY_FIREBASE_TOKEN = "key.firebase.token"
        private const val KEY_USER_INFO = "App.Pref.User.Info"
        private const val KEY_DECIMAL_SEPARATOR = "Key.Decimal.Separator"

        @Volatile
        private var INSTANCE: AppPrefs? = null

        fun shared() =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: AppPrefs().also {
                    INSTANCE = it
                }
            }
    }
//
//    var authInfo: String?
//        set(value) {
//            if (value != null) {
//                //pref[KEY_AUTH] = value
//            }
//        }
//        get() {
//            return pref[KEY_AUTH]
//        }

    fun logout() {
        pref.edit().remove(KEY_AUTH).apply()
    }

}