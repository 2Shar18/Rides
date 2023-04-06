package com.tushar.rides.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tushar.rides.data.model.Vehicle
import com.tushar.rides.data.repository.VehicleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel() : ViewModel() {

    private val _vehicleList = MutableLiveData<List<Vehicle>>()
    val vehicleList: LiveData<List<Vehicle>> = _vehicleList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun generateVehicles(numVehicles: Int) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val vehicles = withContext(Dispatchers.IO) {
                    val vehicleRepository = VehicleRepository()
                    vehicleRepository.generateVehicles(numVehicles)
                }
                _vehicleList.postValue(vehicles)
            } catch (e: Exception) {
                _error.value = e.message
            }
            finally {
                _loading.value = false
            }
        }
    }
}