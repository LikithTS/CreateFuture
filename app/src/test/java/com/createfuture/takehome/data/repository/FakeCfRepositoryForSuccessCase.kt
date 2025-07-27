package com.createfuture.takehome.data.repository

import com.createfuture.takehome.data.models.characterDto.CharacterResponseItem
import com.createfuture.takehome.data.util.JsonFileReader
import com.createfuture.takehome.domain.repository.CFRepository
import com.createfuture.takehome.domain.util.NetworkApiErrors
import com.createfuture.takehome.domain.util.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FakeCfRepositoryForSuccessCase : CFRepository {

    private val gson = Gson()
    private val jsonFileReader = JsonFileReader()

    override suspend fun getCharacterData(): Result<List<CharacterResponseItem>, NetworkApiErrors> {
        val jsonData = jsonFileReader.readSampleJsonFromResource("Sample.json")
        val listType = object : TypeToken<List<CharacterResponseItem>>() {}.type
        val characterResponse: List<CharacterResponseItem> = gson.fromJson(jsonData, listType)
        return Result.Success(characterResponse)
    }
}