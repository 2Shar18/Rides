package com.tushar.rides.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tushar.rides.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.generateButton.setOnClickListener {
            val numVehicles = binding.numOfVehicles.text.toString().toInt()
            viewModel.generateVehicles(numVehicles)
        }

        viewModel.vehicleList.observe(viewLifecycleOwner) { vehicleList ->
            binding.vehicleList.adapter = VehicleAdapter(vehicleList)
            binding.tvList.visibility = View.VISIBLE
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            if (!error.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Error generating vehicles", Toast.LENGTH_SHORT).show()
            }
        }
    }

}