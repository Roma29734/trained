package com.example.trained.ui.screen.workout.finishedWorkout

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentFinishedWorkoutBinding
import com.example.trained.utils.Utils.formattedWatchWidget

class FinishedWorkoutFragment :
    BaseFragment<FragmentFinishedWorkoutBinding>(FragmentFinishedWorkoutBinding::inflate) {

    private val args: FinishedWorkoutFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.widget.apply {
//            Установка иконок
            imageView1.setImageResource(R.drawable.ic_timer)
            imageView2.setImageResource(R.drawable.ic_restart)
            imageView3.setImageResource(R.drawable.ic_clock)

//            Установка текста
            val formattedTimeWorkout = formattedWatchWidget((args.dayWorkoutModel.timeWorkout * 1000))
            text1.text = formattedTimeWorkout

            text2.text = args.dayWorkoutModel.completedApproach.toString()

            val formattedTimeChill = formattedWatchWidget((args.dayWorkoutModel.timeChill))
            text3.text = formattedTimeChill

            textNameWorkout.text = args.dayWorkoutModel.idWorkout.toString()
        }

        binding.upBar.textView6.text = "Тренировка"

        binding.materialButton.setOnClickListener {
            val action = FinishedWorkoutFragmentDirections.actionFinishedWorkoutFragmentToNavFragment()
            mainNavController.navigate(action)
        }
    }
}