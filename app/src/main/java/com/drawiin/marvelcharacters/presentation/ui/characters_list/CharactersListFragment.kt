package com.drawiin.marvelcharacters.presentation.ui.characters_list

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.drawiin.marvelcharacters.databinding.FragmentCharactersListBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersListFragment : Fragment() {
    private lateinit var binding: FragmentCharactersListBinding

    private val viewModel: CharactersListViewModel by viewModels()

    private lateinit var carouselAdapter: CarouselAdapter
    private lateinit var characterListAdapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        setupUi()
        subscribeUi()
        setupScrollListener()
        return binding.root
    }

    private fun setupUi() {
        carouselAdapter = CarouselAdapter()
        binding.carousel.adapter = carouselAdapter
        TabLayoutMediator(binding.carouselIndicator, binding.carousel) { _, _ -> }.attach()

        characterListAdapter = CharactersAdapter()
        binding.charactersList.adapter = characterListAdapter

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.reloadPage()
        }
    }

    private fun subscribeUi() {
        viewModel.run {
            charactersCarousel.observe(viewLifecycleOwner, carouselAdapter::submitList)
            charactersList.observe(viewLifecycleOwner) {
                characterListAdapter.submitList(it)
            }
            dialog.observe(viewLifecycleOwner, ::showDialog)
            charactersCarouselLoading.observe(viewLifecycleOwner) { loading ->
                if (loading) binding.carouselLoading.show()
                else {
                    binding.carouselLoading.hide()
                    binding.swipeRefresh.isRefreshing = false
                }
            }

            charactersListLoading.observe(viewLifecycleOwner) { loading ->
                if (loading) binding.charactersListLoading.show()
                else binding.charactersListLoading.hide()
            }
        }
    }

    private fun setupScrollListener() {
        binding.nestedScroll.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { scrollView, _, scrollY, _, _ ->
                val totalScrollHeight = scrollView.getChildAt(0).measuredHeight
                val scrollViewHeight = scrollView.measuredHeight
                val maxScroll = totalScrollHeight - scrollViewHeight
                viewModel.listScrolled(scrollY, maxScroll)
            }
        )
    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(context).apply {
            setTitle("Something went wrong")
            setMessage(message)
            setPositiveButton(android.R.string.ok, null)
        }.show()
    }
}