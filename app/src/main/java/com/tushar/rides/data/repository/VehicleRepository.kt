package com.tushar.rides.data.repository

import android.util.Log
import com.tushar.rides.data.model.Vehicle
import com.tushar.rides.ui.main.MainFragment
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class VehicleRepository {

    fun generateVehicles(numVehicles: Int): List<Vehicle> {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://random-data-api.com/api/vehicle/random_vehicle?size=$numVehicles")
            .build()

        try {
            val response = client.newCall(request).execute()
            val jsonResponse = response.body()?.string()
            if (jsonResponse != null) {
                val vehicles = mutableListOf<Vehicle>()
                val jsonArray = JSONArray(jsonResponse)
                for (i in 0 until jsonArray.length()) {
                    val jsonVehicle = jsonArray.getJSONObject(i)
                    val vehicle = Vehicle(
                        jsonVehicle.getInt("id"),
                        jsonVehicle.getString("vin"),
                        jsonVehicle.getString("make_and_model"),
                        jsonVehicle.getString("color"),
                        jsonVehicle.getString("car_type"),
                    )
                    vehicles.add(vehicle)
                }
                return vehicles.sortedWith(compareBy { it.vin })
            }
        } catch (e: IOException) {
            Log.e("API", "Error fetching vehicles: ${e.message}")
        } catch (e: Exception) {
            Log.e("API", "Error parsing JSON: ${e.message}")
        }
        return emptyList()
    }
}