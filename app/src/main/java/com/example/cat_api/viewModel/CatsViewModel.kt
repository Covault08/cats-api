package com.example.cat_api.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cat_api.data.model.CatsModelItem
import com.example.cat_api.domain.GetCatsUseCase
import com.example.cat_api.domain.UiState
import kotlinx.coroutines.CoroutineDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsViewModel  @Inject constructor(
private val getCats: GetCatsUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _catsState = MutableStateFlow<UiState<List<CatsModelItem>>>(UiState.LOADING)
    val catsState = _catsState.asStateFlow()

    init {
        viewModelScope.launch(dispatcher) {
            retrieveCats()
        }
    }

    fun retrieveCats(
        limit: Int = 10,
        hasBreeds: Int = 10
    ){
        viewModelScope.launch(dispatcher){
            getCats(hasBreeds, limit).collect{
                _catsState.value = it
            }
        }
    }
}