package com.tushar.rides.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tushar.rides.data.model.Vehicle
import com.tushar.rides.databinding.FragmentVehicleBinding

class VehicleAdapter(private val vehicles: List<Vehicle>) : RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {

    private lateinit var binding: FragmentVehicleBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentVehicleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vehicle = vehicles[position]
        holder.bind(vehicle)
    }

    override fun getItemCount(): Int = vehicles.size

    inner class ViewHolder(private val binding: FragmentVehicleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(vehicle: Vehicle) {
            binding.makeModel.text = vehicle.make_and_model
            binding.vin.text = vehicle.vin
            binding.root.setOnClickListener{
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(vehicle)
                it.findNavController().navigate(action)
            }
        }
    }
}