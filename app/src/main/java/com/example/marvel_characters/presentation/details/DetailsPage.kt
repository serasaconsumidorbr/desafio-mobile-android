package com.example.marvel_characters.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.compose.AsyncImage
import com.example.marvel_characters.R
import com.example.marvel_characters.domain.models.Characters

class DetailsPage: Fragment() {

    private lateinit var character: Characters
    private val args: DetailsPageArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        setContent {
            character = args.character

            Column {
                IconButton(onClick = { requireActivity().onBackPressed() },
                modifier = Modifier.padding(start = 20.dp, top = 10.dp)){
                    Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                        contentDescription = "Voltar", Modifier.size(30.dp))
                }
                AsyncImage(model = character.image,
                    contentDescription = character.name,
                    modifier = Modifier.padding(30.dp))
                Text(text = character.name, color = Color.Black,
                fontSize = 24.sp, modifier = Modifier.padding(start = 30.dp))
                Text(text = character.description, color = Color.Gray,
                fontSize = 22.sp, modifier = Modifier
                        .padding(horizontal = 30.dp).fillMaxWidth())
            }
        }
    }
}