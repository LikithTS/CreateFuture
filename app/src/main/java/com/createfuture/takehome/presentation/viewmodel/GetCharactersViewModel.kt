package com.createfuture.takehome.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.createfuture.takehome.domain.model.CFUiState
import com.createfuture.takehome.domain.usecase.GetCharactersUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.createfuture.takehome.domain.util.Result

class GetCharactersViewModel (
    private val getCharactersUseCases: GetCharactersUseCases
): ViewModel() {

    private val _uiState = MutableStateFlow<CFUiState>(CFUiState.Ideal)
    val uiState = _uiState.asStateFlow()

    fun getCharactersData() {
        _uiState.value = CFUiState.Loading
        viewModelScope.launch {
            val dataResponse = getCharactersUseCases()
            when(dataResponse) {
                is Result.Success -> {
                    //We are already handling empty response in use case. If response is empty or null we're sending failure daya
                    _uiState.value = CFUiState.Success(dataResponse.data ?: emptyList())
                }
                is Result.Failure -> {
                    _uiState.value = CFUiState.Error(dataResponse.error)
                }
            }
        }
    }
}