package com.example.trained.ui.screen.mainApp.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentProfileBinding
import com.example.trained.ui.MainViewModel
import com.example.trained.ui.screen.mainApp.nav.NavFragmentDirections
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.profileData.collectLatest { result ->
                    binding.textPersonal.text =
                        "${result.profileData?.age} лет\nвес: ${result.profileData?.weight}кг\nрост: ${result.profileData?.growth}"
                    binding.textName.text = result.profileData?.name

                    binding.textNumsOfWorkout.text = "${result.trainedOfWeek} тренировки в неделю"
                }
            }
        }
    }
}