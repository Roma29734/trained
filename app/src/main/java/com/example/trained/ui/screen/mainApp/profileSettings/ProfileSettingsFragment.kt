package com.example.trained.ui.screen.mainApp.profileSettings

import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentProfileSettingsBinding
import com.example.trained.ui.MainViewModel

class ProfileSettingsFragment :
    BaseFragment<FragmentProfileSettingsBinding>
        (FragmentProfileSettingsBinding::inflate) {

    private val viewModel: MainViewModel by activityViewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.matButtonNext.setOnClickListener {
            if (checkInputPol()) {

                viewModel.inputUser(
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

    private fun checkInputPol(): Boolean {
        return !(
                isEmpty(binding.tiAge.text)
                        && isEmpty(binding.tiGrowth.text)
                        && isEmpty(binding.tiName.text)
                        && isEmpty(binding.tiWeight.text))
    }
}