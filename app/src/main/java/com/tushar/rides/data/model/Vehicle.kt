package com.tushar.rides.data.model

import java.io.Serializable

data class Vehicle(
    val id: Int,
    val vin: String,
    val make_and_model: String,
    val color: String,
    val car_type: String,
    val kilometrage: Int
): Serializable