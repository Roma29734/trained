package com.example.trained.ui.screen.dayConfig


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentDayConfigBinding
import com.example.trained.ui.adapter.WorkoutConfigAdapter
import com.example.trained.ui.adapter.WorkoutStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DayConfigFragment :
    BaseFragment<FragmentDayConfigBinding>
        (FragmentDayConfigBinding::inflate) {

    private val viewModel: DayConfigViewModel by viewModels()
    private val adapter = WorkoutConfigAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerTraine.adapter = adapter
        binding.recyclerTraine.layoutManager = GridLayoutManager(context, 2)

        viewModel.getDayWorkout()
        viewModel.dayWorkout.observe(viewLifecycleOwner) { result ->
            adapter.setWorkout(result)
        }

        binding.matButtonNext2.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_dayConfigFragment_to_addWorkoutFragment)
        }
    }
}