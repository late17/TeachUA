package com.teachuacompose.repository.mainActivity

import android.content.SharedPreferences
import javax.inject.Inject

class MainActivityRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : MainActivityRepositoryInterface {

    override fun getDarkTheme(): Boolean {
        return sharedPreferences.getBoolean("theme", false)
    }

    override fun getIsLocalDatabase(): Boolean {
        return sharedPreferences.getBoolean("isLocalDatabase", false)
    }

    override fun changeDarkTheme(isDarkTheme : Boolean) {
        val edit = sharedPreferences.edit()
        edit.putBoolean("theme", isDarkTheme)
        edit.apply()
    }

    override fun changeIsLocalDatabase(isLocalDatabase : Boolean) {
        val edit = sharedPreferences.edit()
        edit.putBoolean("isLocalDatabase", isLocalDatabase)
        edit.apply()
    }
}
