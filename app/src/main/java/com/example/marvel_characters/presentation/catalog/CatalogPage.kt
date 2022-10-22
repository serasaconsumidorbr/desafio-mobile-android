package com.example.marvel_characters.presentation.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    private var isNetworkAvailable = true
    private lateinit var headerList: List<Characters>
    private lateinit var bodyList: List<Characters>

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {

        viewModel.getAllCharacters()
        setContent {
            headerList = viewModel.headerList
            bodyList = viewModel.bodyList

            val state = rememberPagerState()

            LazyColumn(modifier = Modifier.background(color = Color.Black)) {

                item {
                    HorizontalPager(count = 5, state = state) { page ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(25.dp)) {
                            if(headerList.size > page){
                                AsyncImage(model = headerList[page].image,
                                    contentDescription = headerList[page].name)
                            }
                        }
                    }
                }

                item {
                    Row(horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)) {
                        DotsIndicator(
                            totalDots = 5,
                            selectedIndex = state.currentPage,
                        )
                    }
                }

                items(bodyList.size){ index ->
                    AsyncImage(model = bodyList[index].image,
                        contentDescription = bodyList[index].name,
                        modifier = Modifier.padding(10.dp))
                }

            }
        }
    }
}