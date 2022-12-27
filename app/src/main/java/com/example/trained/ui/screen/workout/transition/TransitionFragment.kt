package com.example.trained.ui.screen.workout.transition

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentTransitionBinding
import com.example.trained.utils.Utils.formattedWatchWidget
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TransitionFragment :
    BaseFragment<FragmentTransitionBinding>(FragmentTransitionBinding::inflate) {

    private val args: TransitionFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Настройка виджета
        binding.widget.apply {
//            установка иконок
            imageView1.setImageResource(R.drawable.ic_dumbbell)
            imageView2.setImageResource(R.drawable.ic_restart)
            imageView3.setImageResource(R.drawable.ic_clock)

//            Установка текстов
            text1.text = args.workout.workoutModel.receptions.toString()
            text2.text = args.workout.workoutModel.sumApproach.toString()
            textNameWorkout.text = args.workout.workoutModel.nameWorkout
//            Установка текста время отдыха
            val formattedTime = formattedWatchWidget((args.workout.timeChill))
            text3.text = formattedTime
        }

//        настройка upBar
        binding.upBar.apply {
            textView6.text = "Тренировка"
            imageButton.setOnClickListener {
                mainNavController.popBackStack()
            }
        }

//        Обработка нажатия кнопки "Начать тренировку"
        binding.materialButton.setOnClickListener {
//            Создание модели для перехода
            val action = TransitionFragmentDirections.actionTransitionFragmentToStopwatchFragment(args.workout)
            mainNavController.navigate(action)
        }
    }
}