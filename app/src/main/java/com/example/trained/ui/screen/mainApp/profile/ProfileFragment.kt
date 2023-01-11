package com.example.trained.ui.screen.mainApp.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentProfileBinding


class ProfileFragment :
    BaseFragment<FragmentProfileBinding>
        (FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels {viewModelFactory}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.include3.textView6.text = "Профиль"

        viewModel.readProfile()
        viewModel.profileData.observe(viewLifecycleOwner) { result ->
            binding.textPersonal.text =
                "${result.age} лет\nвес: ${result.weight}кг\nрост: ${result.growth}"
            binding.textName.text = result.name
        }
    }
}