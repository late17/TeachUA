package com.teachuacompose.domain.mainActivity

import com.teachuacompose.repository.mainActivity.MainActivityRepositoryInterface
import javax.inject.Inject

class MainActivityUseCases @Inject constructor(
    private val repository: MainActivityRepositoryInterface
    ) : MainActivityUseCasesInterface {

    override fun getDarkTheme(): Boolean {
        return repository.getDarkTheme()
    }

    override fun getIsLocalDatabase(): Boolean {
        return repository.getIsLocalDatabase()
    }

    override fun changeDarkTheme(isDarkTheme: Boolean) {
        repository.changeDarkTheme(isDarkTheme)
    }

    override fun changeIsLocalDatabase(isLocalDatabase: Boolean) {
        repository.changeIsLocalDatabase(isLocalDatabase)
    }
}