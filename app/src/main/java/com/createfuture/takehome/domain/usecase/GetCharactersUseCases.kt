package com.createfuture.takehome.domain.usecase

import com.createfuture.takehome.data.models.characterDto.CharacterResponseItem
import com.createfuture.takehome.domain.repository.CFRepository
import com.createfuture.takehome.domain.util.NetworkApiErrors
import com.createfuture.takehome.domain.util.Result

class GetCharactersUseCases(
    private val cfRepository: CFRepository
) {

    suspend operator fun invoke(): Result<List<CharacterResponseItem>, NetworkApiErrors> {
        val charactersResponse = cfRepository.getCharacterData()
        when(charactersResponse) {
            is Result.Success -> {
                val charactersData = charactersResponse.data
                charactersData?.let {
                    return Result.Success(charactersData)
                }
                return Result.Failure(NetworkApiErrors.FAILURE)
            }
            is Result.Failure -> {
                return Result.Failure(charactersResponse.error)
            }
        }
    }
}