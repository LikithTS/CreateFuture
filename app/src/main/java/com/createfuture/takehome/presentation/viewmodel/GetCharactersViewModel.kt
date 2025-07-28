package com.createfuture.takehome.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.createfuture.takehome.domain.model.CFUiState
import com.createfuture.takehome.domain.usecase.GetCharactersUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.createfuture.takehome.domain.util.Result

class GetCharactersViewModel(
    private val getCharactersUseCases: GetCharactersUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow<CFUiState>(CFUiState.Ideal)
    val uiState = _uiState.asStateFlow()

    private val _currentSearchQuery = MutableStateFlow<String>("")
    val currentSearchQuery = _currentSearchQuery.asStateFlow()

    fun getCharactersData() {
        _uiState.value = CFUiState.Loading
        viewModelScope.launch {
            val dataResponse = getCharactersUseCases()
            when (dataResponse) {
                is Result.Success -> {
                    //We are already handling empty response in use case. So no need to worry here.
                    // Since our Success have nullable. We need to pass empty string here again
                    _uiState.value = CFUiState.Success(
                        dataResponse.data ?: emptyList(),
                        completeApiCharacters = dataResponse.data ?: emptyList()
                    )
                    if (_currentSearchQuery.value.isNotEmpty()) {
                        filterListBySearchData(currentSearchQuery.value)
                    }
                }

                is Result.Failure -> {
                    _uiState.value = CFUiState.Error(dataResponse.error)
                }
            }
        }
    }

    /**
     * Following method is used to filter list on ehe complete least. This will be copied to UI State and reference it from any where in presentation layer
     */
    fun filterListBySearchData(searchString: String) {
        _currentSearchQuery.value = searchString

        val state = _uiState.value
        if (state is CFUiState.Success) {
            val completeData = state.completeApiCharacters
            val filteredFinalData = if (_currentSearchQuery.value.isNotEmpty()) {
                completeData.filter {
                    it.name.contains(searchString, ignoreCase = true)
                }
            } else {
                completeData
            }
            _uiState.value = CFUiState.Success(filteredFinalData, completeData)
        }
    }
}