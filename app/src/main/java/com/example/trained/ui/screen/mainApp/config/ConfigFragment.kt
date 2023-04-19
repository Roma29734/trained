package com.example.trained.ui.screen.mainApp.config

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentConfigBinding
import com.example.trained.ui.adapter.ConfigDayAdapter
import com.example.trained.ui.screen.mainApp.nav.NavFragmentDirections

class ConfigFragment :
    BaseFragment<FragmentConfigBinding>
        (FragmentConfigBinding::inflate) {

    private val viewModel: ConfigViewModel by viewModels {viewModelFactory}
    private val adapter = ConfigDayAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.upBar.textView6.text = getString(R.string.configTrained)
        binding.recyclerTraine.adapter = adapter

        adapter.clickInFirstButton = {
            val action = NavFragmentDirections.actionNavFragmentToDayConfigFragment2(it)
            mainNavController.navigate(action)
        }

        viewModel.getDayWorkout()
        viewModel.dayWorkout.observe(viewLifecycleOwner) { result ->
            Log.d("configFragment","$result")
            adapter.setConfigDay(result)
        }
    }
}