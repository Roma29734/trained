package com.example.trained.ui.screen.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentNavBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NavFragment : BaseFragment<FragmentNavBinding>(FragmentNavBinding::inflate) {

    private val viewModel: NavViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            if(viewModel.checkUser()) {
                if(viewModel.checkWorkout()) {
                    val navView = binding.bottomNavigationView
                    val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragments) as NavHostFragment
                    val navController = navHostFragment.findNavController()

                    navView.setupWithNavController(navController)
                } else {
                    Navigation.findNavController(view).navigate(R.id.action_navFragment_to_dayConfigFragment)
                }
            } else {
                Navigation.findNavController(view).navigate(R.id.action_navFragment_to_profileSettingsFragment)
            }
        }
    }
}