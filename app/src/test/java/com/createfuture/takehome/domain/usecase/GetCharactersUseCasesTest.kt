package com.createfuture.takehome.domain.usecase

import com.createfuture.takehome.data.repository.FakeCfRepositoryCase2ForSuccessCase
import com.createfuture.takehome.data.repository.FakeCfRepositoryForFailureCase
import com.createfuture.takehome.data.repository.FakeCfRepositoryForSuccessCase
import com.createfuture.takehome.domain.repository.CFRepository
import com.createfuture.takehome.domain.util.NetworkApiErrors
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import com.createfuture.takehome.domain.util.Result

class GetCharactersUseCasesTest {

    private lateinit var useCase: GetCharactersUseCases
    private lateinit var cFRepository: CFRepository

    @Test
    fun `returns success data when api for get character is successful`() = runTest {
        cFRepository = FakeCfRepositoryForSuccessCase()
        useCase = GetCharactersUseCases(cFRepository)
        when(val responseData = useCase()) {
            is Result.Success -> {
                Assert.assertEquals("Eddard Stark", responseData.data?.first()?.name)
            }
            is Result.Failure -> {
                Assert.fail("Expected success but got error: ${responseData.error}")
            }
        }
    }

    @Test
    fun `returns empty list if server not able procees the request in a given time`() = runTest {

        cFRepository = FakeCfRepositoryCase2ForSuccessCase()
        useCase = GetCharactersUseCases(cFRepository)
        when(val responseData = useCase()) {
            is Result.Success -> {
                Assert.assertEquals(0, responseData.data?.size)
            }
            is Result.Failure -> {
                Assert.fail("Expected success but got error: ${responseData.error}")
            }
        }
    }

    @Test
    fun `return failure when get character data is failure`() = runTest {
        cFRepository = FakeCfRepositoryForFailureCase()
        useCase = GetCharactersUseCases(cFRepository)
        when(val responseData = useCase()) {
            is Result.Success -> {
                Assert.fail("Expected failure but got success: ${responseData.data}")
            }
            is Result.Failure -> {
                Assert.assertEquals(NetworkApiErrors.FAILURE, responseData.error)
            }
        }
    }

}