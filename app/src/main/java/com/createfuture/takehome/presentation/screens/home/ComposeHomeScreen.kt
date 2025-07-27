package com.createfuture.takehome.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.createfuture.takehome.domain.model.CFUiState
import com.createfuture.takehome.presentation.viewmodel.GetCharactersViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ComposeHomeScreen(
    modifier: Modifier,
    getCharactersViewModel: GetCharactersViewModel = koinViewModel<GetCharactersViewModel>()
) {

    val cfState by getCharactersViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        getCharactersViewModel.getCharactersData()
    }

    when(val dataResp = cfState) {
        is CFUiState.Ideal -> {
            //Do nothing
            //Since it is default value.
        }
        is CFUiState.Loading -> {
            //Show Progress
            Box(modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is CFUiState.Success -> {
            //Show main content view
            CharacterDataView(modifier, dataResp.apiCharacters, onQueryChanged = {
                getCharactersViewModel.filterListBySearchData(it)
            })
        }
        is CFUiState.Error -> {
            //Handle error
            HandleErrorView(dataResp.message)
        }
    }
}