package com.example.trained.ui.screen.mainApp.addWorkout

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.data.toDomain
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentAddWorkoutBinding



class AddWorkoutFragment :
    BaseFragment<FragmentAddWorkoutBinding>
        (FragmentAddWorkoutBinding::inflate) {

    private val viewModel: AddWorkoutViewModel by viewModels {viewModelFactory}
    private val args: AddWorkoutFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.matButtonSave.setOnClickListener {
            if (checkInputPol()) {
                viewModel.saveWorkout(
                    binding.tiNameWorkout.text.toString(),
                    binding.tiRepetitions.text.toString(),
                    binding.tiApproaches.text.toString(),
                    args.workout.toDomain(),
                    binding.tiProjectileWeight.text.toString(),
                )

                Toast.makeText(context, "Успешно добавлено", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).popBackStack()
            }
        }

        binding.upBar.imageButton.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

    }

    private fun checkInputPol(): Boolean {
        return !(
                TextUtils.isEmpty(binding.tiNameWorkout.text)
                        && TextUtils.isEmpty(binding.tiApproaches.text)
                        && TextUtils.isEmpty(binding.tiRepetitions.text)
                        && TextUtils.isEmpty(binding.tiProjectileWeight.text)
                )
    }
}