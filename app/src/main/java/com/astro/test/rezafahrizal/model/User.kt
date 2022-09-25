package com.astro.test.rezafahrizal.model

import com.google.gson.annotations.SerializedName

data class User(

    @field:SerializedName("login")
    val login: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("avatar_url")
    val avatar_url: String? = null,
)
