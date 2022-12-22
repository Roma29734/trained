package com.example.trained.ui.screen.workout.transition

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentTransitionBinding


class TransitionFragment :
    BaseFragment<FragmentTransitionBinding>(FragmentTransitionBinding::inflate) {

    private val args: TransitionFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.widget.apply {
            textApproach.text = args.workout.approaches.toString()
            textNameWorkout.text = args.workout.nameExercise
            textRepetitions.text = args.workout.repetitions.toString()
            textTime.text = args.workout.timeChill
        }

        binding.materialButton.setOnClickListener {
            Toast.makeText(context, "Тута скоро будет переход", Toast.LENGTH_SHORT).show()
        }
    }
}