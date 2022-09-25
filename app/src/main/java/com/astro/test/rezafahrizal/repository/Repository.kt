package com.astro.test.rezafahrizal.repository

import android.content.Context
import com.astro.test.rezafahrizal.annotation.DataTypeAnnotation
import com.astro.test.rezafahrizal.model.User
import com.astro.test.rezafahrizal.model.UserFavorite
import com.astro.test.rezafahrizal.model.UserLocal

class Repository(context: Context) {
    private var appDatabase: AppDatabase

    init {
        appDatabase = AppDatabase.getInstance(context)
    }

    suspend fun retrieveUserLocal(mode: String): List<UserLocal>? {
        val result :List<UserLocal>? = if (mode == DataTypeAnnotation.SortMode.ASC) {
            appDatabase.parametersDao().loadUserLocalByAsc()
        } else {
            appDatabase.parametersDao().loadUserLocalByDesc()
        }

        return result
    }

    suspend fun saveToLocal(list: List<User?>?) {
        val resultFilter = ArrayList<UserLocal>()
        appDatabase.parametersDao().deleteUserLocal()
        addFaveUserToLocal()
        // filtering
        for (it in list ?: return) {
            val data = appDatabase.parametersDao().findUserFavorite(it?.login ?: "")
            if (data == null) {
                val local = UserLocal(
                    0, it?.login, it?.login.toString().trim(), it?.avatar_url, 0
                )
                appDatabase.parametersDao().insertUserLocal(local)
                resultFilter.add(local)
            }
        }

    }

    private suspend fun addFaveUserToLocal() {
        val faveUser = appDatabase.parametersDao().loadUserFavourite()
        if (!faveUser.isNullOrEmpty()) {
            faveUser.forEach {
                val local = UserLocal(
                    0, it.login.toString().trim(), it.id, it.avatar_url, 1
                )
                appDatabase.parametersDao().insertUserLocal(local)
            }
        }
    }

    suspend fun addToFave(userLocal: UserLocal?) {
        val fave = UserFavorite(0, userLocal?.login.toString(), userLocal?.id, userLocal?.avatar_url)
        appDatabase.parametersDao().insertUserFavourite(fave)
    }

    suspend fun removeFromFave(userLocal: UserLocal?) {
        appDatabase.parametersDao().deleteUserFavourite(userLocal?.login.toString())
    }

    suspend fun clearDatabase() {
        appDatabase.parametersDao().deleteUserLocal()
    }


}