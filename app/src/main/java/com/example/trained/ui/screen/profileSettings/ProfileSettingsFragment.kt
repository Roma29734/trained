package com.example.trained.ui.screen.profileSettings

import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.text.TextUtils.isEmpty
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentProfileSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileSettingsFragment :
    BaseFragment<FragmentProfileSettingsBinding>
        (FragmentProfileSettingsBinding::inflate) {

    private val viewModel: ProfileSettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.matButtonNext.setOnClickListener {
            if (checkInputPol()) {

                viewModel.inputuser(
                    name = binding.tiName.text.toString(),
                    age = binding.tiAge.text.toString(),
                    weight = binding.tiWeight.text.toString(),
                    growth = binding.tiGrowth.text.toString(),
                )

                Navigation.findNavController(view)
                    .navigate(R.id.action_profileSettingsFragment_to_navFragment)

            } else {
                Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun checkInputPol(): Boolean {
        return !(
                isEmpty(binding.tiAge.text)
                        && isEmpty(binding.tiGrowth.text)
                        && isEmpty(binding.tiName.text)
                        && isEmpty(binding.tiWeight.text))
    }
}