package com.example.trained.ui.screen.workout.stopwatch

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.trained.base.BaseFragment
import com.example.trained.data.model.DayWorkoutModel
import com.example.trained.data.model.WorkoutTransitionModel
import com.example.trained.databinding.FragmentStopwatchBinding
import com.example.trained.utils.Utils.formattedTimeMain

class StopwatchFragment :
    BaseFragment<FragmentStopwatchBinding>(FragmentStopwatchBinding::inflate) {

    private val interval = 1000
    private var mHandler: Handler? = null
    private var timeInSeconds = 0L
    private var startButtonClicked = false
    private val args: StopwatchFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.upBar.textView6.text = "Тренировка"

        binding.materialButton.setOnClickListener {
            if (!startButtonClicked) {
//                Запускаю таймер
                startTimer()
                startTimerView()
            } else {
//                Перехожу на TimeChillFragment

//                Собираю обновленную модель
                Log.d("checkTime", timeInSeconds.toString())
                val model = WorkoutTransitionModel(
                    DayWorkoutModel(
                        id = args.transit.workoutModel.id,
                        idWorkout = args.transit.workoutModel.idWorkout,
                        nameWorkout = args.transit.workoutModel.nameWorkout,
                        sumApproach = args.transit.workoutModel.sumApproach,
                        completedApproach = args.transit.workoutModel.completedApproach + 1,
                        receptions = args.transit.workoutModel.receptions,
                        timeWorkout = args.transit.workoutModel.timeWorkout + timeInSeconds
                    ),
                    args.transit.timeChill
                )
                val action =
                    StopwatchFragmentDirections.actionStopwatchFragmentToTimerChillFragment(
                        model
                    )
                stopTimer()
                mainNavController.navigate(action)
            }
        }
    }

    private var mStatusChecker: Runnable = object : Runnable {
        override fun run() {
            try {
                timeInSeconds += 1
                Log.e("timeInSeconds", timeInSeconds.toString())
                updateStopWatchView(timeInSeconds)
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler!!.postDelayed(this, interval.toLong())
            }
        }
    }

    private fun startTimer() {
        mHandler = Handler(Looper.getMainLooper())
        mStatusChecker.run()
    }

    private fun startTimerView() {

        binding.materialButton.text = "Стоп"
        startButtonClicked = true

    }

    private fun stopTimer() {
        mHandler?.removeCallbacks(mStatusChecker)
    }

    private fun updateStopWatchView(timeInSeconds: Long) {
        val formattedTime = formattedTimeMain((timeInSeconds * 1000))
        Log.e("formattedTime", formattedTime)
        binding.textTime.text = formattedTime
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
    }
}