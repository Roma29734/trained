package com.example.trained.ui.screen.addWorkout

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentAddWorkoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddWorkoutFragment :
    BaseFragment<FragmentAddWorkoutBinding>(FragmentAddWorkoutBinding::inflate) {

    private val viewModel: AddWorkoutViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.matButtonSave.setOnClickListener {
            if(checkInputPol()) {
                viewModel.saveWorkout(
                        binding.tiNameWorkout.text.toString(),
                        binding.tiRepetitions.text.toString(),
                        binding.tiApproaches.text.toString()
                    )

                Toast.makeText(context, "Успешно добавлено", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).popBackStack()
            }
        }

    }

    fun checkInputPol(): Boolean {
        return !(
                TextUtils.isEmpty(binding.tiNameWorkout.text)
                        && TextUtils.isEmpty(binding.tiApproaches.text)
                        && TextUtils.isEmpty(binding.tiRepetitions.text)
                )
    }



}