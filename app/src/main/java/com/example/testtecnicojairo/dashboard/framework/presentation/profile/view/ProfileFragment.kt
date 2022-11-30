package com.example.testtecnicojairo.dashboard.profile.view

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.core.base.fragment.BaseFragment
import com.example.material.delegates.viewBinding
import com.example.testtecnicojairo.dashboard.profile.viewModel.ProfileViewModel
import com.example.testtecnicojairo.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    override val binding: FragmentProfileBinding by viewBinding {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    override val viewModel: ProfileViewModel by viewModels()

    override fun setUp(arguments: Bundle?) {
        super.setUp(arguments)
        viewModel.setUp(arguments)
    }
}