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
    private val viewModel: TransitionViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Настройка виджета
        binding.widget.apply {
//            установка иконок
            imageView1.setImageResource(R.drawable.ic_dumbbell)
            imageView2.setImageResource(R.drawable.ic_restart)
            imageView3.setImageResource(R.drawable.ic_clock)

//            Установка текстов
            text1.text = args.workout.repetitions.toString()
            text2.text = args.workout.approaches.toString()
            textNameWorkout.text = args.workout.nameExercise
//            Установка текста время отдыха
            val formattedTime = formattedWatchWidget((args.workout.timeChill))
            text3.text = formattedTime
        }

//        настройка upBar
        binding.upBar.textView6.text = "Тренировка"

//        Обработка нажатия кнопки "Начать тренировку"
        binding.materialButton.setOnClickListener {
//            Создание модели для перехода
            viewModel.createWorkout(
                args.workout.id,
                args.workout.approaches,
                args.workout.timeChill
            )

            viewModel.userId.observe(viewLifecycleOwner) { result ->
                if (result != null) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val model = viewModel.getWorkoutById(result.toInt())
                        val action =
                            TransitionFragmentDirections.actionTransitionFragmentToStopwatchFragment(
                                model
                            )
                        lifecycleScope.launch (Dispatchers.Main) {
                            mainNavController.navigate(action)
                        }
                    }
                }
            }
        }
    }
}