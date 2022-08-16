package com.alexis.shop.utils.prefs

import android.content.Context
import android.content.SharedPreferences

class SheredPreference (private val  context : Context) {
    companion object {
        private const val PREF_NAME = "ALEXIS"
        private const val PREF_TOKEN = "ALEXIS_TOKEN"
    }
    private val sharedPref : SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    fun saveToken(token : String) {
        put(PREF_TOKEN, token)
    }
    fun getToken() : String {
        return get(PREF_TOKEN, String::class.java)
    }

    private fun <T> get(key: String, clazz: Class<T>): T =
        when (clazz) {
            String::class.java -> sharedPref.getString(key, "")
            Boolean::class.java -> sharedPref.getBoolean(key, false)
            Float::class.java -> sharedPref.getFloat(key, -1f)
            Double::class.java -> sharedPref.getFloat(key, -1f)
            Int::class.java -> sharedPref.getInt(key, -1)
            Long::class.java -> sharedPref.getLong(key, -1L)
            else -> null
        } as T

    private fun <T> put(key: String, data: T) {
        val editor = sharedPref.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }
}