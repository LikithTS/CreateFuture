package com.createfuture.takehome.presentation.viewmodel

import com.createfuture.takehome.data.models.characterDto.CharacterResponseItem
import com.createfuture.takehome.data.util.JsonFileReader
import com.createfuture.takehome.domain.model.CFUiState
import com.createfuture.takehome.domain.usecase.GetCharactersUseCases
import com.createfuture.takehome.domain.util.NetworkApiErrors
import com.createfuture.takehome.domain.util.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCharactersViewModelTest {

    private lateinit var viewModel: GetCharactersViewModel
    private val getCharactersUseCases = mockk<GetCharactersUseCases>()
    private val gson = Gson()
    private val jsonFileReader = JsonFileReader()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = GetCharactersViewModel(getCharactersUseCases)
    }

    @Test
    fun `should return character list when fetch is successful`() = runTest {

        coEvery {
            getCharactersUseCases()
        } returns Result.Success(getCharacterData())

        viewModel.getCharactersData()

        advanceUntilIdle()

        val state = viewModel.uiState.value
        when (state) {
            is CFUiState.Success -> {
                Assert.assertEquals(32, state.completeApiCharacters.size)
            }
            is CFUiState.Error -> {
                //It should not return failure in this case
            }
            is CFUiState.Ideal -> {
                //It is not ideal
            }
            is CFUiState.Loading -> {
                //It is loading state
            }
        }
    }

    @Test
    fun `should return failure when fetch fails`() = runTest{
        coEvery {
            getCharactersUseCases()
        } returns Result.Failure(NetworkApiErrors.FAILURE)

        viewModel.getCharactersData()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        when (state) {
            is CFUiState.Success -> {
               //It should not return failure in this case
            }
            is CFUiState.Error -> {
                Assert.assertEquals(NetworkApiErrors.FAILURE, state.message)
            }
            is CFUiState.Ideal -> {
                //It is not ideal
            }
            is CFUiState.Loading -> {
                //It is loading state
            }
        }
    }

    @Test
    fun `get filtered list by search data`() {
        coEvery {
            getCharactersUseCases()
        } returns Result.Success(getCharacterData())

        viewModel.filterListBySearchData("Ca")

        val state = viewModel.uiState.value
        when (state) {
            is CFUiState.Success -> {
                Assert.assertEquals("Catelyn Stark", state.apiCharacters[0].name)
            }
            is CFUiState.Error -> {
                //It should not return failure in this case
            }
            is CFUiState.Ideal -> {
                //It is not ideal
            }
            is CFUiState.Loading -> {
                //It is loading state
            }
        }
    }

    private fun getCharacterData(): List<CharacterResponseItem> {
        val jsonData = jsonFileReader.readSampleJsonFromResource("Sample.json")
        val listType = object : TypeToken<List<CharacterResponseItem>>() {}.type
        val characterData: List<CharacterResponseItem> = gson.fromJson(jsonData, listType)
        return characterData
    }


}