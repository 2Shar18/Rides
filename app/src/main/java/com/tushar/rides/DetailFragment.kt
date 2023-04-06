package com.tushar.rides

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tushar.rides.data.model.Vehicle
import com.tushar.rides.databinding.BottomSheetDialogBinding
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
            carbonBottomSheet.setOnClickListener {
                val dialog = BottomSheetDialog(requireContext())
                val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
                val tvKilometer = view.findViewById<TextView>(R.id.tvKilometer)
                val km = vehicle.kilometrage.toDouble()
                tvKilometer.text = "This vehicle has driven "+String.format("%.0f", km)+" KM."
                val tvCarbonEmission = view.findViewById<TextView>(R.id.tvCarbonEmission)
                var carbonEmission:Double = km;
                if (km > 5000) {
                    carbonEmission += ((km - 5000) * 0.5)
                }
                tvCarbonEmission.text = "Estimated carbon emission is "+String.format("%.2f", carbonEmission)+" units."
                dialog.setCancelable(true)
                dialog.setContentView(view)
                dialog.show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}