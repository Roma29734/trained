package com.example.trained.ui.screen.firstStartApp.dayAddWorkout

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.data.toDomain
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentDayAddWorkoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DayAddWorkoutFragment :
    BaseFragment<FragmentDayAddWorkoutBinding>
        (FragmentDayAddWorkoutBinding::inflate) {

    private val viewModel: DayAddWorkoutViewModel by viewModels()
    private val args: DayAddWorkoutFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.matButtonSave.setOnClickListener {
            if (checkInputPol()) {
                viewModel.saveWorkout(
                    binding.tiNameWorkout.text.toString(),
                    binding.tiRepetitions.text.toString(),
                    binding.tiApproaches.text.toString(),
                    args.workout.toDomain(),
                )

                Toast.makeText(context, "Успешно добавлено", Toast.LENGTH_SHORT).show()
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