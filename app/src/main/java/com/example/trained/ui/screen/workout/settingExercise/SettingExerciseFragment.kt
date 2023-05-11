package com.example.trained.ui.screen.workout.settingExercise

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.akexorcist.snaptimepicker.SnapTimePickerDialog
import com.akexorcist.snaptimepicker.TimeRange
import com.akexorcist.snaptimepicker.TimeValue
import com.akexorcist.snaptimepicker.extension.SnapTimePickerUtil
import com.example.trained.base.BaseFragment
import com.example.data.model.transit.WorkoutTransitionModel
import com.example.trained.R
import com.example.trained.databinding.FragmentSettingExerciseBinding


class SettingExerciseFragment :
    BaseFragment<FragmentSettingExerciseBinding>
        (FragmentSettingExerciseBinding::inflate) {

    private val args: SettingExerciseFragmentArgs by navArgs()

    private var timeChill: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textTimeChill.text = "0мин"
        binding.textNameExercise.text = "Упражнение ${args.workout.workoutModel.nameWorkout}"
        binding.textApproach.text = args.workout.workoutModel.sumApproach.toString()
        binding.textRepetitions.text = args.workout.workoutModel.receptions.toString()
        binding.upBar.imageButton.setOnClickListener { mainNavController.popBackStack() }

        binding.materialButton.setOnClickListener {
            if (timeChill == null) {
                Toast.makeText(context, "Введите время отдыха между подходами", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val model = WorkoutTransitionModel(
                    workoutModel = args.workout.workoutModel,
                    timeChill = timeChill!!.toLong(),
                    timeWorkout = 0,
                )
                val action =
                    SettingExerciseFragmentDirections
                        .actionSettingExerciseFragmentToTransitionFragment(
                            model
                        )
                mainNavController.navigate(action)
            }
        }

        binding.buttonTime.setOnClickListener {
            SnapTimePickerDialog.Builder().apply {
                setTitle(R.string.title)
                setPrefix(R.string.time_suffix)
                setSuffix(R.string.time_prefix)
                setThemeColor(R.color.secondAccent)
                setTitleColor(R.color.black)
                setPreselectedTime(TimeValue(0, 0))
                setSelectableTimeRange(TimeRange(TimeValue(0, 0), TimeValue(24, 60)))
//                useViewModel()
            }.build().apply{
                setListener { hour, minute ->
                    // Do something when user selected the time
                    Toast.makeText(context, "$hour $minute", Toast.LENGTH_SHORT).show()
                    timeChill = if(hour == 0 && minute == 0) {
                        null
                    } else{
                        if(hour == 0) {
                            binding.textTimeChill.text = "${minute}мин"
                            minute * 60000
                        } else {
                            binding.textTimeChill.text = "${hour}ч ${minute}мин"
                            ((hour * 60) + minute) * 60000
                        }
                    }
                }
            }.show(childFragmentManager, tag)
        }
    }
}

