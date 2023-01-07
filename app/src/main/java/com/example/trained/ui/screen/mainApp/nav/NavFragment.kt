package com.example.trained.ui.screen.mainApp.nav

import android.os.Bundle
import android.util.Log
import android.view.View
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
class NavFragment :
    BaseFragment<FragmentNavBinding>
        (FragmentNavBinding::inflate) {

    private val viewModel: NavViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            if (viewModel.checkUser()) {
                Log.d("checkBagFirstStart", "Зашел в есть профиль")
                if (viewModel.checkWorkout()) {
                    binding.navHostFragments.visibility = View.VISIBLE
                    val navView = binding.bottomNavigationView
                    val navHostFragment =
                        childFragmentManager.findFragmentById(R.id.nav_host_fragments) as NavHostFragment
                    val navController = navHostFragment.findNavController()

                    navView.setupWithNavController(navController)
                } else {
                    mainNavController.navigate(R.id.action_navFragment_to_dayConfigFragment)
                }
            } else {
                Log.d("checkBagFirstStart", "Зашел в нет профиля")
                Navigation.findNavController(view)
                    .navigate(R.id.action_navFragment_to_startFragment)
                Log.d("checkBagFirstStart", "совершил навигации в настройки профиля")
            }
        }
    }
}