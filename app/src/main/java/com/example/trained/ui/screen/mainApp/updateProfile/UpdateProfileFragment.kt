package com.example.trained.ui.screen.mainApp.updateProfile

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.trained.R
import com.example.trained.base.BaseFragment
import com.example.trained.databinding.FragmentUpdateProfileBinding
import com.example.trained.ui.MainViewModel

class UpdateProfileFragment :
    BaseFragment<FragmentUpdateProfileBinding>
        (FragmentUpdateProfileBinding::inflate) {

    private val viewModel: MainViewModel by activityViewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            upBar.textView6.text = getText(R.string.profileSettings)
            upBar.imageButton.setOnClickListener { mainNavController.popBackStack() }
            matButtonNext.setOnClickListener {
                if (checkInputPol()) {
                    viewModel.updateProfile(
                        age = tiAge.text.toString(),
                        weight = tiWeight.text.toString(),
                        growth = tiGrowth.text.toString(),
                    )

                    mainNavController.popBackStack()
                } else {
                    Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun checkInputPol(): Boolean {
        return !(
                TextUtils.isEmpty(binding.tiAge.text)
                        && TextUtils.isEmpty(binding.tiGrowth.text)
                        && TextUtils.isEmpty(binding.tiWeight.text))
    }
}