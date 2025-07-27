package com.createfuture.takehome.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.createfuture.takehome.R
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.createfuture.takehome.data.models.characterDto.CharacterResponseItem
import com.createfuture.takehome.domain.util.SessionConstant

@Composable
fun CharacterDataView(
    modifier: Modifier,
    apiCharacters: List<CharacterResponseItem>,
    onQueryChanged: (String) -> Unit) {

    var query by remember { mutableStateOf("") }

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.img_characters),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Black.copy(alpha = 0.5f))
        )

        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            SearchView(query = query, onQueryChanged = {
                query = it
                onQueryChanged(it)
            })

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(apiCharacters) { character ->
                    CharacterItem(character)
                    HorizontalDivider(color = Color.Gray.copy(alpha = 0.5f), thickness = 0.5.dp)
                }
            }
        }
    }
}

@Composable
fun CharacterItem(character: CharacterResponseItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = character.name,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            InfoRow(stringResource(R.string.culture), character.culture)
            InfoRow(stringResource(R.string.born), character.born)
            InfoRow(stringResource(R.string.died),
                character.died.ifBlank { stringResource(R.string.still_alive) })
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(stringResource(R.string.session), color = Color.White, fontSize = 14.sp)
            val seasons = character.tvSeries.joinToString("") {
                when (it) {
                    SessionConstant.SEASON_1 -> "I, "
                    SessionConstant.SEASON_2 -> "II, "
                    SessionConstant.SEASON_3 -> "III, "
                    SessionConstant.SEASON_4 -> "IV, "
                    SessionConstant.SEASON_5 -> "V, "
                    SessionConstant.SEASON_6 -> "VI, "
                    SessionConstant.SEASON_7 -> "VII, "
                    SessionConstant.SEASON_8 -> "VIII, "
                    else -> ""
                }
            }.removeSuffix(", ")
            Text(seasons, color = Color.White, fontSize = 14.sp)
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row {
        Text(
            text = label,
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = value,
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 14.sp
        )
    }
}
