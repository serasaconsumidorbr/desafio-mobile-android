package com.example.marvel_characters.presentation.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
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
            isNetworkAvailable = viewModel.isNetworkAvailable.value

            val state = rememberPagerState()

            LazyColumn(modifier = Modifier.background(color = Color.Black)) {

                item {
                    HorizontalPager(count = 5, state = state) { page ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(25.dp)) {
                            if(headerList.size > page){
                                AsyncImage(model = headerList[page].image(),
                                    contentDescription = headerList[page].name,
                                modifier = Modifier.clickable {
                                    goToDetailsPage(headerList[page])
                                })
                            } else {
                                Spacer(modifier = Modifier.padding(50.dp))
                                CircularProgressIndicator(modifier = Modifier.size(50.dp),
                                    color = Color.Red)
                                Spacer(modifier = Modifier.padding(50.dp))
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
                    AsyncImage(model = bodyList[index].image(),
                        contentDescription = bodyList[index].name,
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                goToDetailsPage(bodyList[index])
                            })
                    if(index == bodyList.size -1){
                        viewModel.getNewCharacters()
                    }
                }
                item {
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.Center){
                        CircularProgressIndicator(modifier = Modifier.size(50.dp),
                            color = Color.Red)
                    }

                }
            }

            if(!isNetworkAvailable){
                Toast.makeText(requireContext(), "Servidor indisponivel, verifique a conexao!",
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun goToDetailsPage(character: Characters){
        requireView().findNavController().navigate(
            CatalogPageDirections.actionCatalogFragmentToDetailsFragment(character))
    }
}