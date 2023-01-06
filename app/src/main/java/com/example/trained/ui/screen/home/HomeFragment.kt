package com.example.trained.ui.screen.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Transformations.map
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentHomeBinding
import com.example.trained.ui.adapter.WorkoutStateAdapter
import com.example.trained.utils.Utils.formattedWatchWidget
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding>
        (FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private val adapter = WorkoutStateAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

        viewModel.readProfile()
        viewModel.readDayWorkout()

        viewModel.profileData?.observe(viewLifecycleOwner) { model ->
            if (model != null) {
                binding.textName.text = model.name
            }
        }

        viewModel.dailyStatistics.observe(viewLifecycleOwner) { result ->

            adapter.setWorkout(result.workout)
            var completeApproach = 0
            var sumApproach = 0
            result.workout.map {
                sumApproach += 1
                if (it.completedApproach == it.sumApproach) {
                    completeApproach += 1
                }
            }
            setUiWidget(result.timeWorkout, completeApproach, sumApproach)
        }

        binding.materialButton.setOnClickListener {
            mainNavController.navigate(R.id.action_navFragment_to_choseWorkoutFragment)
        }
    }

    private fun setUiWidget(time: Long?, completeApproach: Int, sumApproach: Int) {

        binding.mainWidget.apply {
            if (time != null) {
                val formatedTime = formattedWatchWidget(time * 1000)
                textTime.text = formatedTime
            }
            textApproach.text = "${completeApproach}/${sumApproach}"
            textWeight.text = "50kg"
        }
    }
}