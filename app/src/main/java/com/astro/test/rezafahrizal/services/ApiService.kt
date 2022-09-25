package com.astro.test.rezafahrizal.services

import com.astro.test.rezafahrizal.model.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    // user list
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun loadUser(
        @Query("q") query: String
    ): Single<UserResponse>

}