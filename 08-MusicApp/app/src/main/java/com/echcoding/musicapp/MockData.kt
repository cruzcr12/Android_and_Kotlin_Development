package com.echcoding.musicapp

import androidx.annotation.DrawableRes

data class MockData(
    @DrawableRes val image: Int,
    val name: String
)

val libraries = listOf<MockData>(
    MockData(R.drawable.ic_playlist_play, "Playlist"),
    MockData(R.drawable.ic_microphone, "Artists"),
    MockData(R.drawable.ic_music_library, "Albums"),
    MockData(R.drawable.ic_music_note, "Songs"),
    MockData(R.drawable.ic_instant_mix, "Genre"),
)
