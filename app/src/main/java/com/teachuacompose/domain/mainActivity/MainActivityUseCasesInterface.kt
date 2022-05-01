package com.teachuacompose.domain.mainActivity

interface MainActivityUseCasesInterface {

    fun getDarkTheme():Boolean

    fun getIsLocalDatabase() : Boolean

    fun changeDarkTheme(isDarkTheme: Boolean)

    fun changeIsLocalDatabase(isLocalDatabase : Boolean)

}