package com.createfuture.takehome.data.repository

import com.createfuture.takehome.data.models.characterDto.CharacterResponseItem
import com.createfuture.takehome.data.remote.CFApiInterface
import com.createfuture.takehome.domain.repository.CFRepository
import com.createfuture.takehome.domain.util.NetworkApiErrors
import com.createfuture.takehome.domain.util.Result
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

/**
 * Get the implementation of CFRepository
 */
class CFRepositoryImpl(
    private val cfApiInterface: CFApiInterface
) : CFRepository {
    override suspend fun getCharacterData(): Result<List<CharacterResponseItem>, NetworkApiErrors> {
        return try {
            val charactersData = cfApiInterface.getCharacters()
            if(charactersData.isSuccessful) {
                //Handling success case here
                Result.Success(charactersData.body())
            } else {
                //Handling error case here
                val errorCode = charactersData.code()
                when(errorCode) {
                    401 -> Result.Failure(NetworkApiErrors.UN_AUTHORISED)
                    404 -> Result.Failure(NetworkApiErrors.NOT_FOUND)
                    else -> Result.Failure(NetworkApiErrors.FAILURE)
                }
            }
        } catch (e : Exception) {
            coroutineContext.ensureActive()
                e.printStackTrace()
            Result.Failure(NetworkApiErrors.GenericException(e.message ?: "Failure"))
        }
    }
}