package com.example.trained.ui.screen.workout.settingExercise

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.trained.base.BaseFragment
import com.example.trained.data.model.WorkoutTransitionModel
import com.example.trained.databinding.FragmentSettingExerciseBinding


class SettingExerciseFragment :
    BaseFragment<FragmentSettingExerciseBinding>(FragmentSettingExerciseBinding::inflate) {

    private val args: SettingExerciseFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textNameExercise.text = "Упражнение ${args.workout.nameExercise}"
        binding.textApproach.text = args.workout.approaches.toString()
        binding.textRepetitions.text = args.workout.repetitions.toString()


        binding.materialButton.setOnClickListener {
            if (binding.tiTimeChill.text?.isEmpty() == null) {
                Toast.makeText(context, "Введите время отдыха между подходами", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val model = WorkoutTransitionModel(
                    args.workout.nameExercise,
                    args.workout.repetitions,
                    args.workout.approaches,
                    binding.tiTimeChill.text.toString()
                )
                val action =
                    SettingExerciseFragmentDirections.actionSettingExerciseFragmentToTransitionFragment(model)
                mainNavController.navigate(action)
            }
        }

    }
}