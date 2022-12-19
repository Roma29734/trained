package com.example.trained.ui.screen.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.include3.textView6.text = "Профиль"

        viewModel.readProfile()
        viewModel.profileData.observe(viewLifecycleOwner) { result ->
            binding.textPersonal.text = "${result.age} лет\nвес: ${result.weight}кг\nрост: ${result.growth}"
            binding.textName.text = result.name
        }
    }
}