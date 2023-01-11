package com.example.trained.ui.screen.mainApp.dayConfig

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.data.model.transit.TransitUpdateWorkoutModel
import com.example.data.toTransit
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentDayConfigBinding
import com.example.trained.ui.adapter.WorkoutConfigAdapter
import com.example.trained.utils.Utils.getDecryptedWeekAccusativeForm


class DayConfigFragment :
    BaseFragment<FragmentDayConfigBinding>
        (FragmentDayConfigBinding::inflate) {

    private val viewModel: DayConfigViewModel by viewModels {viewModelFactory}
    private val adapter = WorkoutConfigAdapter()
    private val args: DayConfigFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerWorkout.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerWorkout.adapter = adapter

        args.week.apply {
            val testData = getDecryptedWeekAccusativeForm(this)
            binding.include2.textView6.text = "Конфигурация $testData"
            viewModel.readWorkout(this)
        }
        viewModel.workout.observe(viewLifecycleOwner) { result ->
            adapter.callBackDel = {
                val model = TransitUpdateWorkoutModel(
                    workoutModel = result.toTransit(),
                    id = it
                )
                val action =
                    DayConfigFragmentDirections.actionDayConfigFragmentToUpdateConfigFragment(model)
                mainNavController.navigate(action)
            }
            binding.matButtonNext2.setOnClickListener {
                val action =
                    DayConfigFragmentDirections.actionDayConfigFragmentToAddWorkoutFragment(result.toTransit())
                mainNavController.navigate(action)
            }
            adapter.setWorkout(result.workout)
        }

        binding.include2.imageButton.setOnClickListener {
            mainNavController.popBackStack()
        }
    }
}