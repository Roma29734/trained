package com.example.trained.ui.screen.mainApp.updateConfig

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.data.model.transit.TransitWorkoutModel
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentUpdateConfigBinding

class UpdateConfigFragment :
    BaseFragment<FragmentUpdateConfigBinding>
        (FragmentUpdateConfigBinding::inflate) {

    private val viewModel: UpdateConfigViewModel by viewModels { viewModelFactory }
    private val args: UpdateConfigFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            upBar.textView6.text = "Изменение тренировки"

            upBar.imageButton.setOnClickListener { mainNavController.popBackStack() }

            tiNameWorkout.setText(args.workout.workoutModel.workout[args.workout.id].nameExercise)
            tiApproaches.setText(args.workout.workoutModel.workout[args.workout.id].approaches.toString())
            tiRepetitions.setText(args.workout.workoutModel.workout[args.workout.id].repetitions.toString())

            matButtonEdit.setOnClickListener {
                if (checkInputPol()) {
                    if (
                        binding.tiNameWorkout.text.toString() == args.workout.workoutModel.workout[args.workout.id].nameExercise &&
                        binding.tiApproaches.text.toString() == args.workout.workoutModel.workout[args.workout.id].approaches.toString() &&
                        binding.tiRepetitions.text.toString() == args.workout.workoutModel.workout[args.workout.id].repetitions.toString()
                    ) {
                        Toast.makeText(context, "Вы ничего не изминили", Toast.LENGTH_SHORT).show()
                    } else {
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
            matButtonDelete.setOnClickListener {
                deleteUser(
                    id = args.workout.id,
                    workout = args.workout.workoutModel
                )
            }
        }
    }

    private fun deleteUser(id: Int, workout: TransitWorkoutModel) {
        val builder = AlertDialog.Builder(context)
        builder.setPositiveButton("Да") { _, _ ->
            viewModel.deleteWorkout(id, workout)
            Toast.makeText(
                context,
                "успешно удалено: ",
                Toast.LENGTH_SHORT
            ).show()
            mainNavController.popBackStack()
        }
        builder.setNegativeButton("Нет") { _, _ -> }
        builder.setTitle("Удалить ?")
        builder.setMessage("Вы хотите удалить упражнение?")
        builder.create().show()
    }

    private fun checkInputPol(): Boolean {
        return !(
                TextUtils.isEmpty(binding.tiNameWorkout.text)
                        && TextUtils.isEmpty(binding.tiApproaches.text)
                        && TextUtils.isEmpty(binding.tiRepetitions.text)
                )
    }
}