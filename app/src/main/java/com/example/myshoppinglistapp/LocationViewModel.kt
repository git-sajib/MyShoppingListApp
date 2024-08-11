package com.example.myshoppinglistapp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

class LocationViewModel: ViewModel() {
    private val  _location = mutableStateOf<LocationData?>(null)
    val location:State<LocationData?> = _location

    private val _address = mutableStateOf(listOf<GeocodingResult>())
    val address: State<List<GeocodingResult>> = _address

    fun updateLocation(newLocation: LocationData){
        _location.value = newLocation
    }

    fun fetchAddress(latLng: String){
        try {
            viewModelScope.launch {
                val  result = RetrofitClient.create().getAddressFromCoordinates(
                    latLng,
                    "AIzaSyDaaZkUmeLm3s6YzJ41iAO_qkz4SVkDDRo"
                )
                _address.value = result.results
            }
        }catch(e: Exception){
            Log.d("res1", "${e.cause} ${e.message}")
        }
    }
}