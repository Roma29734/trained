package com.example.trained.ui.screen.mainApp.updateConfig

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentUpdateConfigBinding



class UpdateConfigFragment :
    BaseFragment<FragmentUpdateConfigBinding>
        (FragmentUpdateConfigBinding::inflate) {

    private val viewModel: UpdateConfigViewModel by viewModels {viewModelFactory}
    private val args: UpdateConfigFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.include5.textView6.text = "Изменение тренировки"

        binding.tiNameWorkout.setText(args.workout.workoutModel.workout[args.workout.id].nameExercise)
        binding.tiApproaches.setText(args.workout.workoutModel.workout[args.workout.id].approaches.toString())
        binding.tiRepetitions.setText(args.workout.workoutModel.workout[args.workout.id].repetitions.toString())
//
        binding.matButtonSave.setOnClickListener {
            if(checkInputPol()) {
                viewModel.updateWorkout(
                    name = binding.tiNameWorkout.text.toString(),
                    approaches = binding.tiApproaches.text.toString(),
                    repetitions = binding.tiRepetitions.text.toString(),
                    id = args.workout.id,
                    workoutModel = args.workout.workoutModel,
                )
                mainNavController.popBackStack()
            }
        }
    }

    private fun checkInputPol(): Boolean {
        return !(
                TextUtils.isEmpty(binding.tiNameWorkout.text)
                        && TextUtils.isEmpty(binding.tiApproaches.text)
                        && TextUtils.isEmpty(binding.tiRepetitions.text)
                )
    }
}