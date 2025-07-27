package com.createfuture.takehome.domain.model

import com.createfuture.takehome.data.models.characterDto.CharacterResponseItem
import com.createfuture.takehome.domain.util.NetworkApiErrors

sealed class CFUiState {

    object Ideal : CFUiState()
    object Loading : CFUiState()
    data class Success(
        val apiCharacters: List<CharacterResponseItem>,
        val completeApiCharacters: List<CharacterResponseItem>
    ) : CFUiState()

    data class Error(val message: NetworkApiErrors) : CFUiState()

}