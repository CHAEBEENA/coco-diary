package com.chaebeen.coco.data.prefs

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {

    val PREFERENCES_NAME = "coco_preference"

    var isLock: Boolean = false

    private val password : String = "1234"

    /*
    val prefs : SharedPreferences

    init {
        prefs = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }
*/

    private fun getPreferences(context: Context) : SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun setPassword(context: Context, key: String, value: String) {
        val prefs = getPreferences(context)
        prefs.edit().putString(key, value).apply() /*commit*/
        setIsLock(context, true)
    }

    fun getPassword(context: Context, key: String): String? {
        val prefs = getPreferences(context)
        return prefs.getString(key, password)
    }

    private fun setIsLock(context: Context, isLock: Boolean){
        val prefs = getPreferences(context)
        prefs.edit().putBoolean("IS_LOCK", isLock).apply()
    }

    fun removeKey(context: Context, key: String) {
        val prefs = getPreferences(context)
        prefs.edit().remove(key).apply()
    }


}