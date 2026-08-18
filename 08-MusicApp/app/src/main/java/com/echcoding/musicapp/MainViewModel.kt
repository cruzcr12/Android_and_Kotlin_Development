package com.echcoding.musicapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _currentState: MutableState<Screen> = mutableStateOf(Screen.DrawerScreen.AddAccount)

    val currentScreen: MutableState<Screen>
        get() = _currentState

    fun setCurrentScreen(screen: Screen){
        _currentState.value = screen
    }
}
