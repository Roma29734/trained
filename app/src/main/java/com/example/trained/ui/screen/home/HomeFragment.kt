package com.example.trained.ui.screen.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentHomeBinding
import com.example.trained.ui.adapter.WorkoutStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

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

        viewModel.dayWorkout.observe(viewLifecycleOwner) {
            adapter.setWorkout(it)
        }

        binding.materialButton.setOnClickListener {
            mainNavController.navigate(R.id.action_navFragment_to_choseWorkoutFragment)
        }
    }
}