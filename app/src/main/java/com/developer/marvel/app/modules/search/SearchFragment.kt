package com.developer.marvel.app.modules.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.developer.marvel.app.BaseFragment
import com.developer.marvel.app.modules.account.MaintenanceViewModel
import com.developer.marvel.databinding.FragmentDetailBinding
import com.developer.marvel.databinding.FragmentMaintenanceBinding

class SearchFragment: BaseFragment() {

    private var _binding: FragmentMaintenanceBinding? = null
    private val binding get() = _binding!!

    private val maintenanceViewModel: MaintenanceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMaintenanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.maintenanceImage.setImageResource(maintenanceViewModel.getRandomBackground())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}