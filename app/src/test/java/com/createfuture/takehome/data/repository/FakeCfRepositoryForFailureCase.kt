package com.createfuture.takehome.data.repository

import com.createfuture.takehome.data.models.characterDto.CharacterResponseItem
import com.createfuture.takehome.domain.repository.CFRepository
import com.createfuture.takehome.domain.util.NetworkApiErrors
import com.createfuture.takehome.domain.util.Result

class FakeCfRepositoryForFailureCase : CFRepository {

    override suspend fun getCharacterData(): Result<List<CharacterResponseItem>, NetworkApiErrors> {
        return Result.Failure(NetworkApiErrors.FAILURE)
    }
}