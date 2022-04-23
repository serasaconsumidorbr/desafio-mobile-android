package com.br.leandro.marvel_hero_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.br.leandro.marvel_hero_app.databinding.FragmentDetailBinding
import com.br.leandro.marvel_hero_app.extensions.getValueOrDefault
import com.br.leandro.marvel_hero_app.extensions.load
import com.br.leandro.marvel_hero_app.datasource.network.model.Character
import com.br.leandro.marvel_hero_app.datasource.network.model.Comic
import com.br.leandro.marvel_hero_app.datasource.network.model.Comics
import com.br.leandro.marvel_hero_app.ui.viewmodel.activity.SharedViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

import com.fernandohbrasil.marvelsquad.ui.viewmodel.fragment.*
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailFragmentArgs>()

    private val sharedViewModel by sharedViewModel<SharedViewModel>()
    private val viewModel by viewModel<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        viewModel.characterStateApi.observe(viewLifecycleOwner, characterStateApiObserver())
        viewModel.comicsStateApi.observe(viewLifecycleOwner, comicsStateApiObserver())

        viewModel.characterHireStateDb.observe(viewLifecycleOwner, characterHireStateDb())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.hasTitleBar(false)

        binding.apply {
            ivClose.setOnClickListener {
                activity?.onBackPressed()
            }

            btHireFire.setOnClickListener {
                viewModel.hireOrFireCharacter()
            }
        }
    }

    private fun characterStateApiObserver(): Observer<CharacterStateApi> = Observer { state ->
        when (state) {
            is CharacterApiStarted -> viewModel.findCharacterApi(args.id)
            is CharacterApiSuccess -> bindCharacter(state.character)
            is CharacterApiError -> Snackbar.make(
                binding.root,
                state.error.toString(),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun comicsStateApiObserver(): Observer<ComicsStateApi> = Observer { state ->
        when (state) {
            is ComicsApiStarted -> viewModel.findComics(args.id)
            is ComicsApiSuccess -> bindAppearsComics(state.comics)
            is ComicsApiError -> Snackbar.make(
                binding.root,
                state.error.toString(),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun characterHireStateDb(): Observer<CharacterHireStateDb> = Observer { state ->
        when (state) {
            is CharacterHireStateDbStarted -> viewModel.findCharacterDb(args.id)
            is CharacterHireStateDbFired -> bindHireFireButton(false)
            is CharacterHireStateDbHired -> bindHireFireButton(true)
            is CharacterHireStateDbError -> Snackbar.make(
                binding.root,
                state.error.toString(),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun bindHireFireButton(hired: Boolean) {
        if (hired) {
            binding.btHireFire.background = resources.getDrawable(R.drawable.bg_fire_button, activity?.theme)
            binding.btHireFire.setText(R.string.fire_from_squad)
        } else {
            binding.btHireFire.background = resources.getDrawable(R.drawable.bg_hire_button, activity?.theme)
            binding.btHireFire.setText(R.string.hire_to_squad)
        }
    }

    private fun bindAppearsComics(comics: Comics) {
        if (comics.data.results.isNotEmpty()) {
            bindComicOne(comics.data.results[0])

            if (comics.data.results.size > 1) {
                bindComicTwo(comics.data.results[1])
                bindOtherAppears(comics)
            }
        }
    }

    private fun bindComicTwo(comic: Comic) {
        binding.tvTitleComicOne.text = getComicTitle(comic.title)
        binding.ivComicTwo.load(comic.thumbnail.portraitImage())
    }

    private fun bindComicOne(comic: Comic) {
        binding.tvTitleComicTwo.text = getComicTitle(comic.title)
        binding.ivComicOne.load(comic.thumbnail.portraitImage())
    }

    private fun bindOtherAppears(comics: Comics) {
        val total = comics.data.total - 2
        if (total > 0) binding.tvOthersComics.text = resources.getQuantityString(R.plurals.numberOfComicsAvailable, total, total)
    }

    private fun bindCharacter(character: Character) {
        binding.apply {
            ivAvatar.load(character.thumbnail.detailImage())
            tvName.text = character.name
            tvDescription.text = getDescription(character.description)
        }
    }

    private fun getDescription(description: String) = description.getValueOrDefault(resources.getString(R.string.no_description_found))

    private fun getComicTitle(description: String) = description.getValueOrDefault(resources.getString(R.string.no_comic_title_found))

    override fun onStart() {
        super.onStart()
        viewModel.start()
    }

    override fun onPause() {
        super.onPause()
        viewModel.finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}