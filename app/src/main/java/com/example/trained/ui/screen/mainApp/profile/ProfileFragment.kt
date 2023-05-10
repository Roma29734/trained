package com.example.trained.ui.screen.mainApp.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentProfileBinding
import com.example.trained.ui.MainViewModel
import com.example.trained.ui.screen.mainApp.nav.NavFragmentDirections

class ProfileFragment :
    BaseFragment<FragmentProfileBinding>
        (FragmentProfileBinding::inflate) {

    private val viewModel: MainViewModel by activityViewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            include3.textView6.text = "Профиль"
            matButtonSettingsProfile.setOnClickListener {
                val action = NavFragmentDirections.actionNavFragmentToAppSettingsFragment()
                mainNavController.navigate(action)
            }
            imgButtonEdit.setOnClickListener {
                val action = NavFragmentDirections.actionNavFragmentToUpdateProfileFragment()
                mainNavController.navigate(action)
            }
        }
        viewModel.readProfile()
        viewModel.profileData.observe(viewLifecycleOwner) { result ->
            binding.textPersonal.text =
                "${result.age} лет\nвес: ${result.weight}кг\nрост: ${result.growth}"
            binding.textName.text = result.name
        }
    }
}