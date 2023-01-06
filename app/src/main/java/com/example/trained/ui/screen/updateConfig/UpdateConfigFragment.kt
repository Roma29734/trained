package com.example.trained.ui.screen.updateConfig

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentUpdateConfigBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateConfigFragment :
    BaseFragment<FragmentUpdateConfigBinding>
        (FragmentUpdateConfigBinding::inflate) {

    private val args: UpdateConfigFragmentArgs by navArgs()
    private val viewModel: UpdateConfigViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.include5.textView6.text = "Изменение тренировки"

//        binding.tiNameWorkout.setText(args.workout.)
//        binding.tiApproaches.setText(args.workout.approaches.toString())
//        binding.tiRepetitions.setText(args.workout.repetitions.toString())
//
//        binding.matButtonSave.setOnClickListener {
//            if(checkInputPol()) {
//                viewModel.updateWorkout(
//                    id = args.workout.id,
//                    name = binding.tiNameWorkout.text.toString(),
//                    approaches = binding.tiApproaches.text.toString().toInt(),
//                    repetitions = binding.tiRepetitions.text.toString().toInt(),
//                )
//                mainNavController.popBackStack()
//            }
//        }

    }

    private fun checkInputPol(): Boolean {
        return !(
                TextUtils.isEmpty(binding.tiNameWorkout.text)
                        && TextUtils.isEmpty(binding.tiApproaches.text)
                        && TextUtils.isEmpty(binding.tiRepetitions.text)
                )
    }
}