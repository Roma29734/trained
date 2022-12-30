package com.example.trained.ui.screen.workout.finishedWorkout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.data.toDomain
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentFinishedWorkoutBinding
import com.example.trained.utils.Utils.formattedWatchWidget
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FinishedWorkoutFragment :
    BaseFragment<FragmentFinishedWorkoutBinding>
        (FragmentFinishedWorkoutBinding::inflate) {

    private val args: FinishedWorkoutFragmentArgs by navArgs()
    private val viewModel: FinishedViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.widget.apply {
//            Установка иконок
            imageView1.setImageResource(R.drawable.ic_timer)
            imageView2.setImageResource(R.drawable.ic_restart)
            imageView3.setImageResource(R.drawable.ic_clock)

//            Установка текста
            val formattedTimeWorkout =
                formattedWatchWidget((args.transit.workoutModel.timeWorkout * 1000))
            text1.text = formattedTimeWorkout

            text2.text = args.transit.workoutModel.completedApproach.toString()

            val formattedTimeChill = formattedWatchWidget((args.transit.timeChill))
            text3.text = formattedTimeChill

            textNameWorkout.text = args.transit.workoutModel.idWorkout.toString()
        }

        binding.upBar.textView6.text = "Тренировка"

        binding.materialButton.setOnClickListener {
            viewModel.updateDayWorkout(args.transit.workoutModel.toDomain())
            val action =
                FinishedWorkoutFragmentDirections.actionFinishedWorkoutFragmentToNavFragment()
            mainNavController.navigate(action)
        }
    }
}