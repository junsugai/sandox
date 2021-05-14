package com.example.jetpackcomposedemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposedemo.navigation.Screens

class MainViewModel : ViewModel() {

    // screen management
//    private val _currentScreen = MutableLiveData<Screens>(Screens.DrawerScreens.Home)
    private val _currentScreen = MutableLiveData<Screens>(Screens.HomeScreens.Favorite)
    val currentScreen: LiveData<Screens> = _currentScreen

    // index of Current Screen
    fun setCurrentScreen(screen: Screens) {
        _currentScreen.value = screen
    }

    // demo
    private val _clickCount = MutableLiveData(0)
    val clickCount: LiveData<Int> = _clickCount

    fun updateClick(value: Int) {
        _clickCount.value = value
    }
}
