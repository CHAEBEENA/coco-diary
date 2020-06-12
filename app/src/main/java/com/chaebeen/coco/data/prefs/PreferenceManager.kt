package com.chaebeen.coco.data.prefs

import android.content.Context
import android.content.SharedPreferences

const val PASSWORD = "password"
const val IS_LOCK = "isLock"

class PreferenceManager(context: Context) {

    val PREFERENCES_NAME = "coco_preference"

    private fun getPreferences(context: Context) : SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun setPassword(context: Context, key: String, value: String) {
        val prefs = getPreferences(context)
        prefs.edit().putString(PASSWORD, value).apply() /*commit*/
        setIsLock(context, true)
    }

    fun getPassword(context: Context, key: String): String? {
        val prefs = getPreferences(context)
        return prefs.getString(key, PASSWORD)
    }

    private fun setIsLock(context: Context, isLock: Boolean){
        val prefs = getPreferences(context)
        prefs.edit().putBoolean(IS_LOCK, isLock).apply()
    }

    fun isLock(context: Context) : Boolean {
        val prefs = getPreferences(context)
        return prefs.getBoolean(IS_LOCK, false)
    }

    fun removeKey(context: Context) {
        val prefs = getPreferences(context)
        prefs.edit().putString(PASSWORD, "").apply() /*commit*/
        setIsLock(context, false)
    }


}