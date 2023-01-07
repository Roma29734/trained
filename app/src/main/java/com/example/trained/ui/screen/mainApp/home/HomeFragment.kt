package com.example.trained.ui.screen.mainApp.home

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentHomeBinding
import com.example.trained.ui.adapter.WorkoutStateAdapter
import com.example.trained.utils.LoadState
import com.example.trained.utils.Utils.formattedWatchWidget
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding>
        (FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private val adapter = WorkoutStateAdapter()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

        viewModel.readDayWorkout()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.dailyWorkoutState.collectLatest { uiState ->
                    when (uiState.loadState) {
                        LoadState.LOADING -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        LoadState.NON_DAILY -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        LoadState.ERROR -> {
                            binding.progressBar.visibility = View.INVISIBLE
                            Toast.makeText(context, "Произошла ошибка перезпустите приложение", Toast.LENGTH_SHORT).show()
                        }
                        LoadState.SUCCESS -> {
                            binding.progressBar.visibility = View.INVISIBLE
                            uiState.successState?.dailyWorkoutModel?.let { adapter.setWorkout(it.workout) }
                            var completeApproach = 0
                            var sumApproach = 0
                            uiState.successState?.dailyWorkoutModel?.workout?.map {
                                sumApproach += 1
                                if (it.completedApproach == it.sumApproach) {
                                    completeApproach += 1
                                }
                            }
                            setUiWidget(
                                uiState.successState?.dailyWorkoutModel?.timeWorkout,
                                completeApproach,
                                sumApproach
                            )
                        }
                    }
                }
            }
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