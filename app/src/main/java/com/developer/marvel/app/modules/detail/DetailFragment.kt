package com.developer.marvel.app.modules.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.developer.marvel.R
import com.developer.marvel.app.BaseFragment
import com.developer.marvel.app.modules.home.helper.MarginItemDecoration
import com.developer.marvel.app.utils.toDateFormat
import com.developer.marvel.databinding.FragmentDetailBinding
import com.developer.marvel.domain.entities.Character
import com.developer.marvel.utils.loadImage

class DetailFragment: BaseFragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupViews(args.character)
    }

    private fun setupToolbar() {
        with(binding.toolbar) {
            (activity as AppCompatActivity).setSupportActionBar(this)
            this.setupWithNavController(navController)
            this.title = args.character.name
        }
    }

    private fun setupViews(character: Character) {
        binding.textName.text = character.name
        binding.textModified.text = character.modified.toDateFormat()
        binding.textDescription.text = character.description.ifBlank { getString(R.string.no_response_description) }

        val baseUrl = character.thumbnail.let {
            "${it.path}/portrait_fantastic.${it.extension}"
        }
        binding.imageView.loadImage(baseUrl)
        binding.imageView.contentDescription = "Foto de: ${character.name}"

        binding.recyclerPresentation.adapter = DetailsAdapter(
            character.comics,
            character.series,
            character.stories,
            character.events
        )
        binding.recyclerPresentation.addItemDecoration(MarginItemDecoration(8))
        binding.recyclerPresentation.itemAnimator = DefaultItemAnimator()
        binding.recyclerPresentation.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}