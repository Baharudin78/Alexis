package com.alexis.shop.data.local

import android.content.Context
import com.alexis.shop.domain.model.auth.LoginModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthLocalDataSource @Inject constructor(context: Context) {

    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val ID = "ID"
        private const val USER_ID = "USER_ID"
        private const val FULL_NAME = "FULL_NAME"
        private const val EMAIL = "EMAIL"
        private const val PHONE = "PHONE"
        private const val BIRTH_DATE = "BIRTH_DATE"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveLoginCredential(loginModel: LoginModel) {
        preferences.edit().apply {
            putInt(ID, loginModel.id)
            putInt(USER_ID, loginModel.userId)
            putString(FULL_NAME, loginModel.fullName)
            putString(EMAIL, loginModel.email)
            putString(PHONE, loginModel.phone)
            putString(BIRTH_DATE, loginModel.birthDate)
        }.apply()
    }

    fun getLoginCredential(): LoginModel {
        return LoginModel(
            id = preferences.getInt(ID, 0),
            userId = preferences.getInt(USER_ID, 0),
            fullName = preferences.getString(FULL_NAME, "").orEmpty(),
            email = preferences.getString(EMAIL, "").orEmpty(),
            phone = preferences.getString(PHONE, "").orEmpty(),
            birthDate = preferences.getString(BIRTH_DATE, "").orEmpty()
        )
    }

    fun getUserId(): Int {
        return preferences.getInt(ID, 0)
    }

    fun isUserLogin(): Boolean {
        val fullName = preferences.getString(FULL_NAME, "")
        return !fullName.isNullOrEmpty()
    }

    fun logout() {
        preferences.edit().clear().apply()
    }
}