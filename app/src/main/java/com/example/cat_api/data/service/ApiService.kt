package com.example.cat_api.data.service

import com.example.cat_api.data.model.CatsModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(PATH)
    suspend fun getCats(
        @Query(HAS_BREEDS) hasBreeds: Int,
        @Query(LIMIT_Q) limit: Int
    ): Response<List<CatsModelItem>>

}