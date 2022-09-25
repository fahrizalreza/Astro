package com.astro.test.rezafahrizal.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "UserFavorite")
data class UserFavorite(
    @ColumnInfo(name = "roomId")
    @PrimaryKey(autoGenerate = true)
    val roomId: Long = 0,

    @field:SerializedName("login")
    val login: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("avatar_url")
    val avatar_url: String? = null
)