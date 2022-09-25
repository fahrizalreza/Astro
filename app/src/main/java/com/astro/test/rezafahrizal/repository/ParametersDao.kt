package com.astro.test.rezafahrizal.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.astro.test.rezafahrizal.model.UserFavorite
import com.astro.test.rezafahrizal.model.UserLocal

@Dao
interface ParametersDao {

    @Query("select * from UserLocal order by favourite desc, login asc")
    suspend fun loadUserLocalByAsc(): List<UserLocal>?

    @Query("select * from UserLocal order by favourite desc, login desc")
    suspend fun loadUserLocalByDesc(): List<UserLocal>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertUserLocal(user: UserLocal?)

    @Query("Delete from UserLocal")
    suspend fun deleteUserLocal()

    @Query("select * from UserFavorite")
    suspend fun loadUserFavourite(): List<UserFavorite>?

    @Query("select * from UserFavorite where login = :user")
    suspend fun findUserFavorite(user: String): UserFavorite?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertUserFavourite(user: UserFavorite?)

    @Query("Delete from UserFavorite where login = :user")
    suspend fun deleteUserFavourite(user: String)
}