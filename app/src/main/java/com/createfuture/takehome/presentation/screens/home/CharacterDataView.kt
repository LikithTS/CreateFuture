package com.createfuture.takehome.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.createfuture.takehome.data.models.characterDto.CharacterResponseItem

@Composable
fun CharacterDataView(apiCharacters: List<CharacterResponseItem>) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_characters),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(apiCharacters) { character ->
                CharacterItem(character = character)
                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }
}

@Composable
fun CharacterItem(character: CharacterResponseItem) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = character.name,
                color = Color.White,
                fontSize = 16.sp
            )
            InfoRow(label = "Culture:", value = character.culture)
            InfoRow(label = "Born:", value = character.born)
            InfoRow(label = "Died:", value = character.died)
        }

        Column {
            Text("Seasons:", color = Color.White, fontSize = 14.sp)
            val seasons = character.tvSeries.joinToString("") {
                when (it) {
                    "Season 1" -> "I, "
                    "Season 2" -> "II, "
                    "Season 3" -> "III, "
                    "Season 4" -> "IV, "
                    "Season 5" -> "V, "
                    "Season 6" -> "VI, "
                    "Season 7" -> "VII, "
                    "Season 8" -> "VIII"
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
        Text(text = label, color = Color.White, fontSize = 16.sp)
        Text(text = value, color = Color.White, fontSize = 16.sp)
    }
}