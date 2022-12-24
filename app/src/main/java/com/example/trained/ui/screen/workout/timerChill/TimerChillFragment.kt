package com.example.trained.ui.screen.workout.timerChill

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentTimerChillBinding
import com.example.trained.utils.Utils.formattedTimeMain


class TimerChillFragment :
    BaseFragment<FragmentTimerChillBinding>(FragmentTimerChillBinding::inflate) {

    private val args: TimerChillFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.upBar.textView6.text = "Тренировка"

        startTimer(args.dayWorkoutModel.timeChill)
    }

    private fun startTimer(time: Long) {

        val timer = object : CountDownTimer(time, 1000) {
            override fun onTick(p0: Long) {
                val formattedTime = formattedTimeMain((p0))
                binding.textTime.text = formattedTime
            }

            override fun onFinish() {
                binding.textViewChillSecond.text = "ПЕРЕРЫВ АКОНЧЕЯН"
                binding.apply {
                    linear.visibility = View.VISIBLE
                }
                timerFinishUi()
            }
        }
        timer.start()
    }

    private fun timerFinishUi() {

        binding.apply {
            materialButtonNext.setOnClickListener {
                if (args.dayWorkoutModel.sumApproach != args.dayWorkoutModel.completedApproach) {
                    val action =
                        TimerChillFragmentDirections.actionTimerChillFragmentToStopwatchFragment(
                            args.dayWorkoutModel
                        )
                    mainNavController.navigate(action)
                } else {
                    val action =
                        TimerChillFragmentDirections.actionTimerChillFragmentToFinishedWorkoutFragment(
                            args.dayWorkoutModel
                        )
                    mainNavController.navigate(action)
                }
            }

            materialButtonComplete.setOnClickListener {
                Toast.makeText(context, "Тут будет досрочное завершение", Toast.LENGTH_SHORT).show()
            }
        }
    }
}