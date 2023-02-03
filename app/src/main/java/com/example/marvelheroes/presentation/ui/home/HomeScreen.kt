package com.example.marvelheroes.presentation.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.marvelheroes.domain.characters.*
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
    val paramsScreen = remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            SearchBar(
                hint = "Search...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) { }
        }
    ) { it ->
        Box(
            Modifier
                .padding(it)
                .fillMaxSize()

        ) {
            AnimatedVisibility(visible = stateUi.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(40.dp)
                )
            }

            AnimatedVisibility(visible = !stateUi.value.isLoading && !changeScreen.value) {
                ContentHome(
                    listHeroesVertically = stateUi.value.listHeroesVertical,
                    listHeroesHorizontally = stateUi.value.listHeroesHorizontally,
                    onClickHorizontal = {
                        changeScreen.value = true
                        paramsScreen.value = it.name
                    },
                    onClickVertical = {
                        changeScreen.value = true
                        paramsScreen.value = it.name
                    }
                )
            }

            AnimatedVisibility(visible = changeScreen.value) {
                Text(
                    text = paramsScreen.value,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(20.dp)
                        .background(Color.Red)
                        .clickable { changeScreen.value = false }
                )
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