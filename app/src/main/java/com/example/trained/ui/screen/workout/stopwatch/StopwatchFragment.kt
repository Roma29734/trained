package com.example.trained.ui.screen.workout.stopwatch

import android.graphics.Color.red
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentStopwatchBinding
import com.example.trained.utils.Utils.getFormattedStopWatch

class StopwatchFragment :
    BaseFragment<FragmentStopwatchBinding>(FragmentStopwatchBinding::inflate) {

    private val interval = 1000
    private var mHandler: Handler? = null
    private var timeInSeconds = 0L
    private var startButtonClicked = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.materialButton.setOnClickListener {
            if (!startButtonClicked) {
//                Запускаю таймер
                startTimer()
                startTimerView()
            } else {
//                Перехожу на фрагмент с отдыхом
                val action =
                    StopwatchFragmentDirections.actionStopwatchFragmentToTimerChillFragment(
                        timeInSeconds
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
        val formattedTime = getFormattedStopWatch((timeInSeconds * 1000))
        Log.e("formattedTime", formattedTime)
        binding.textTime.text = formattedTime
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
    }
}