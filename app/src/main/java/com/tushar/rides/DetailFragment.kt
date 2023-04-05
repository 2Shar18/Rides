package com.tushar.rides

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.tushar.rides.data.model.Vehicle
import com.tushar.rides.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vehicle:Vehicle = args.vehicle
        binding.apply {
            vehicleVin.text = vehicle.vin
            vehicleMakeModel.text = vehicle.make_and_model
            vehicleColor.text = vehicle.color
            vehicleCarType.text = vehicle.car_type
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}