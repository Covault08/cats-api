package com.example.cat_api.domain

import com.example.cat_api.data.model.CatsModelItem
import com.example.cat_api.data.repository.CatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCatsUseCase @Inject constructor(
    private val repository: CatsRepository
) {
    operator fun invoke(
        hasBreeds: Int = 1,
        limit: Int = 10
    ): Flow<UiState<List<CatsModelItem>>> = flow {
        emit(UiState.LOADING)
        val response = repository.getCats(hasBreeds, limit)
        if (response.isSuccessful) {
            response.body()?.let { cats ->
                emit(UiState.SUCCESS(cats))
            } ?: throw Exception("Response body is null")
        } else throw Exception(response.errorBody()?.string())
    }.catch { e ->
        if (e is Exception) {
            emit(UiState.ERROR(e))
        }
    }
}
