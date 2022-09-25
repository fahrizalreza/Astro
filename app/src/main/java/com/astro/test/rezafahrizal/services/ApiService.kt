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