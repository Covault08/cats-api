package com.example.cat_api.data.repository

import com.example.cat_api.data.model.CatsModelItem
import retrofit2.Response

interface CatsRepository {

    suspend fun getCats(
        hasBreeds: Int,
        limit: Int
    ): Response<List<CatsModelItem>>

}