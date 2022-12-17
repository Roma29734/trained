package com.example.trained.ui.screen.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentNavBinding


class NavFragment : BaseFragment<FragmentNavBinding>(FragmentNavBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navView = binding.bottomNavigationView
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragments) as NavHostFragment
        val navController = navHostFragment.findNavController()

        navView.setupWithNavController(navController)
    }
}