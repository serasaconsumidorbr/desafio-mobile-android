package com.example.marvelheroes.presentation.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.marvelheroes.R
import com.example.marvelheroes.presentation.ui.home.components.ContentHome
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()

) {
    val stateUi = viewModel.state.collectAsState()
    val changeScreen = remember {
        mutableStateOf(false)
    }
    val paramsScreenName = remember {
        mutableStateOf("")
    }
    val paramsScreenImage = remember {
        mutableStateOf("")
    }
    val paramsScreenDescription = remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.background(Color.Black)

            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.),
                    contentDescription = "Marvel",
                    contentScale = ContentScale.Fit,
                )
                SearchBar(
                    hint = "Search...",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) { }
            }

        }
    ) { it ->
        Box(
            Modifier
                .padding(it)
                .fillMaxSize()

        ) {
            AnimatedVisibility(visible = stateUi.value.isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(40.dp)
                    )
                }
            }

            AnimatedVisibility(visible = !stateUi.value.isLoading && !changeScreen.value) {
                ContentHome(
                    listHeroesVertically = stateUi.value.listHeroesVertical,
                    listHeroesHorizontally = stateUi.value.listHeroesHorizontally,
                    onClickHorizontal = {
                        changeScreen.value = true
                        paramsScreenName.value = it.name
                        paramsScreenImage.value = "${it.thumbnail.path}.${it.thumbnail.extension}"
                        paramsScreenDescription.value = it.description
                    },
                    onClickVertical = {
                        changeScreen.value = true
                        paramsScreenName.value = it.name
                        paramsScreenImage.value = "${it.thumbnail.path}.${it.thumbnail.extension}"
                        paramsScreenDescription.value = it.description
                    }
                )
            }

            AnimatedVisibility(visible = changeScreen.value) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        onClick = {
                            changeScreen.value = false
                        }
                    ) {
                        Box {
                            AsyncImage(
                                model = paramsScreenImage.value,
                                contentDescription = paramsScreenDescription.value,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                            )

                            Text(
                                text = paramsScreenName.value,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black.copy(alpha = .3f))
                                    .padding(10.dp),
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Justify
                                )
                            )
                        }
                    }
                }
            }
        }

    }

}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}