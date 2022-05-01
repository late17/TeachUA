package com.teachuacompose.repository.mainActivity

interface MainActivityRepositoryInterface {

    fun getDarkTheme():Boolean

    fun getIsLocalDatabase() : Boolean

    fun changeDarkTheme(isDarkTheme: Boolean)

    fun changeIsLocalDatabase(isLocalDatabase : Boolean)

}