package com.example.testtecnicojairo.dashboard.framework.presentation.maps.view

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.core.base.fragment.BaseFragment
import com.example.material.delegates.viewBinding
import com.example.testtecnicojairo.dashboard.framework.presentation.maps.utils.Callbacks
import com.example.testtecnicojairo.dashboard.framework.presentation.maps.utils.hasPermission
import com.example.testtecnicojairo.dashboard.framework.presentation.maps.viewModel.MapViewModel
import com.example.testtecnicojairo.databinding.FragmentMapBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>() {

    override val binding: FragmentMapBinding by viewBinding {
        FragmentMapBinding.inflate(layoutInflater)
    }

    override val viewModel: MapViewModel by viewModels()

    private var activityListener: Callbacks? = null

    override fun setUp(arguments: Bundle?) {
        super.setUp(arguments)
        viewModel.setUp(arguments)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activityListener?.requestBackgroundLocationPermission()
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Callbacks) {
            activityListener = context
            if (!context.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
                //activityListener?.requestFineLocationPermission()
            }
        } else {
            throw RuntimeException("$context must implement LocationUpdateFragment.Callbacks")
        }
    }

    override fun onDetach() {
        super.onDetach()
        activityListener = null
    }

    override fun addViewModelObservables() = with(viewModel) {
        receivingLocationUpdates.observe(this@MapFragment) {
            Toast.makeText(requireActivity(), ""+it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPause() {
        super.onPause()
        if ((viewModel.receivingLocationUpdates.value == true) &&
            (!requireContext().hasPermission(Manifest.permission.ACCESS_BACKGROUND_LOCATION))) {
            viewModel.stopLocationUpdates()
        }
    }
}