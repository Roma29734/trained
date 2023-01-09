package com.example.trained.ui.screen.workout.timerChill

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentTimerChillBinding
import com.example.trained.utils.Utils.formattedTimeMain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class TimerChillFragment :
    BaseFragment<FragmentTimerChillBinding>
        (FragmentTimerChillBinding::inflate) {

    private val args: TimerChillFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.upBar.textView6.text = "Тренировка"

        startTimer(args.transit.timeChill)
    }

    private fun startTimer(time: Long) {

        val timer = object : CountDownTimer(time, 1000) {
            override fun onTick(p0: Long) {
                val formattedTime = formattedTimeMain((p0))
                binding.textTime.text = formattedTime
            }

            override fun onFinish() {
                binding.apply {
                    linear.visibility = View.VISIBLE
                    textViewChillSecond.text = "ПЕРЕРЫВ АКОНЧЕЯН"
                }
                timerFinishUi()
                lifecycleScope.launch (Dispatchers.IO) {
                    while (true) {
                        delay(800)
                        vibratePhone()
                    }
                }
            }
        }
        timer.start()
    }

    private fun timerFinishUi() {

        binding.apply {
            materialButtonNext.setOnClickListener {
                if (args.transit.workoutModel.sumApproach != args.transit.workoutModel.completedApproach) {
                    val action =
                        TimerChillFragmentDirections.actionTimerChillFragmentToStopwatchFragment(
                            args.transit
                        )
                    mainNavController.navigate(action)
                } else {
                    val action =
                        TimerChillFragmentDirections.actionTimerChillFragmentToFinishedWorkoutFragment(
                            args.transit
                        )
                    mainNavController.navigate(action)
                }
            }

            materialButtonComplete.setOnClickListener {
                val action =
                    TimerChillFragmentDirections.actionTimerChillFragmentToFinishedWorkoutFragment(
                        args.transit
                    )
                mainNavController.navigate(action)
            }
        }
    }
}