package com.example.cat_api.data.repository

import com.example.cat_api.data.model.CatsModelItem
import com.example.cat_api.data.service.ApiService
import retrofit2.Response
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(
    private val service: ApiService
): CatsRepository {
    override suspend fun getCats(hasBreeds: Int, limit: Int): Response<List<CatsModelItem>> {
        return service.getCats(hasBreeds, limit)
    }
}