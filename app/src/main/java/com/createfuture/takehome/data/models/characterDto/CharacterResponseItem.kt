package com.createfuture.takehome.data.models.characterDto

data class CharacterResponseItem(
    val aliases: List<String>,
    val born: String,
    val culture: String,
    val died: String,
    val father: String,
    val gender: String,
    val mother: String,
    val name: String,
    val playedBy: List<String>,
    val povBooks: List<Any>,
    val titles: List<String>,
    val tvSeries: List<String>
)