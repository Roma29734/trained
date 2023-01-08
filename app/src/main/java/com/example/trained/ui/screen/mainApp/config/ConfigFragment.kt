package com.example.trained.ui.screen.mainApp.config

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.toTransit
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentConfigBinding
import com.example.trained.ui.adapter.ConfigDayAdapter
import com.example.trained.ui.adapter.WorkoutConfigAdapter
import com.example.trained.ui.screen.mainApp.nav.NavFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfigFragment :
    BaseFragment<FragmentConfigBinding>
        (FragmentConfigBinding::inflate) {

    private val viewModel: ConfigViewModel by viewModels()
    private val adapter = ConfigDayAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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