package com.astro.test.rezafahrizal.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "parent")
data class Parent(
    @ColumnInfo(name = "roomId")
    @PrimaryKey(autoGenerate = true)
    val roomId: Long = 0,

    @ColumnInfo(name = "code")
    @field:SerializedName("code")
    val code: String? = null,

    @ColumnInfo(name = "value2")
    @field:SerializedName("value2")
    val value2: String? = null,

    @ColumnInfo(name = "id")
    @field:SerializedName("id")
    val id: String? = null,

    @ColumnInfo(name = "value")
    @field:SerializedName("value")
    val value: String? = null,

    @ColumnInfo(name = "descriptions")
    @field:SerializedName("descriptions")
    val descriptions: String? = null
)