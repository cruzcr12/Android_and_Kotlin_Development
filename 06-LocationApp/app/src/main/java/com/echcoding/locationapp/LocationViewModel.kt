package com.echcoding.locationapp

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// The ViewModel class handles the communication between the UI and the data layer
class LocationViewModel: ViewModel() {
    private val _location = mutableStateOf<LocationData?>(null)
    val location: State<LocationData?> = _location

    fun updateLocation(newLocation: LocationData){
        _location.value = newLocation
    }

    /*
    fun fetchLocation(locationUtils: LocationUtils){
        locationUtils.requestLocationUpdate{ locationData ->
            updateLocation(locationData)
        }
    }
    */

}

