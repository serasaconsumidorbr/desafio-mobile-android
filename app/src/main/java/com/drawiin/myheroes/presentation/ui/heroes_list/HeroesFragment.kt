package com.drawiin.myheroes.presentation.ui.heroes_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.drawiin.myheroes.databinding.FragmentHeroesBinding


class HeroesFragment : Fragment() {
    private lateinit var binding: FragmentHeroesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeroesBinding.inflate(inflater, container, false)
        return binding.root
    }
}