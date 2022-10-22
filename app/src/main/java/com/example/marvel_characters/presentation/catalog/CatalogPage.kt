package com.example.marvel_characters.presentation.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import coil.compose.AsyncImage
import com.example.marvel_characters.domain.models.Characters
import com.example.marvel_characters.presentation.catalog.components.DotsIndicator
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatalogPage: Fragment() {

    private val viewModel: CatalogViewModel by viewModel()
    private lateinit var charactersList: List<Characters>
    private var isNetworkAvailable = true

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {

        setContent {
            viewModel.getAllCharacters()
            charactersList = viewModel.charactersList

            val state = rememberPagerState()

            Column {
                HorizontalPager(count = 5, state = state) { page ->

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if(charactersList.size > page){
                            AsyncImage(model = charactersList[page].image,
                                contentDescription = charactersList[page].name)
                        }
                    }

                }

                Spacer(modifier = Modifier.padding(4.dp))

                DotsIndicator(
                    totalDots = 5,
                    selectedIndex = state.currentPage,
                )

                LazyColumn {
                    if(charactersList.size > 5){
                        items(charactersList.size - 5){ index ->
                            if(index > 4){
                                AsyncImage(model = charactersList[index].image,
                                    contentDescription = charactersList[index].name)
                            }
                        }
                    }
                }
            }

        }
    }
}