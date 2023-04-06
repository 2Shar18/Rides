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
    private var count: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun isValidCount(ct:Int?) : Boolean {
        return (ct != null && ct in 1 .. 100)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.swipeContainer.setOnRefreshListener {
            if (isValidCount(count)) {
                viewModel.generateVehicles(count)
            }
        }

//        binding.swipeContainer.setColorSchemeColors()

        binding.generateButton.setOnClickListener {
            val numVehicles = binding.numVehicles.text.toString().toIntOrNull()
            if (isValidCount(numVehicles)) {
                count = numVehicles!!.toInt()
                viewModel.generateVehicles(count)
                binding.swipeContainer.isRefreshing = true
            } else {
                Toast.makeText(requireContext(), "Invalid input! Enter a value in range 1 to 100", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.vehicleList.observe(viewLifecycleOwner) { vehicleList ->
            binding.swipeContainer.isRefreshing = false
            if (vehicleList.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Could not load data at this moment! Please try again Later!", Toast.LENGTH_SHORT).show()
            }
            else {
                binding.vehicleList.adapter = VehicleAdapter(vehicleList)
                binding.tvList.visibility = View.VISIBLE
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), "Error generating vehicles", Toast.LENGTH_SHORT).show()
            if (!error.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Error generating vehicles", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            }
            else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

}