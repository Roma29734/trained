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



class FinishedWorkoutFragment :
    BaseFragment<FragmentFinishedWorkoutBinding>
        (FragmentFinishedWorkoutBinding::inflate) {

    private val viewModel: FinishedViewModel by viewModels {viewModelFactory}
    private val args: FinishedWorkoutFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.widget.apply {
//            Установка иконок
            imageView1.setImageResource(R.drawable.ic_timer)
            imageView2.setImageResource(R.drawable.ic_restart)
            imageView3.setImageResource(R.drawable.ic_clock)

//            Установка текста
            val formattedTimeWorkout =
                formattedWatchWidget((args.transit.timeWorkout * 1000))
            text1.text = formattedTimeWorkout

            text2.text = args.transit.workoutModel.completedApproach.toString()


            val formattedTimeChill = formattedWatchWidget((args.transit.timeChill))
            text3.text = formattedTimeChill

            textNameWorkout.text = args.transit.workoutModel.nameWorkout
        }

        binding.upBar.textView6.text = "Тренировка"

        binding.materialButton.setOnClickListener {
            viewModel.updateDayWorkout(args.transit)
            val action =
                FinishedWorkoutFragmentDirections.actionFinishedWorkoutFragmentToNavFragment()
            mainNavController.navigate(action)
        }
    }
}