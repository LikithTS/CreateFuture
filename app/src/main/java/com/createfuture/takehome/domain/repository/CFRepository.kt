package com.createfuture.takehome.domain.repository

import com.createfuture.takehome.data.models.characterDto.CharacterResponseItem
import com.createfuture.takehome.domain.util.NetworkApiErrors
import com.createfuture.takehome.domain.util.Result

interface CFRepository {
    suspend fun getCharacterData() : Result<List<CharacterResponseItem>, NetworkApiErrors>
}