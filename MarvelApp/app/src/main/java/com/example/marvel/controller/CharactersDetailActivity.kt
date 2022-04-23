package com.example.marvel.controller

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.marvel.R
import com.example.marvel.model.Characters
import com.squareup.picasso.Picasso
import java.lang.Exception


class CharactersDetailActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var textTitle: TextView
    lateinit var textID: TextView
    lateinit var textName: TextView
    lateinit var textDescription: TextView
    lateinit var textStories: TextView
    lateinit var imageCharacter: ImageView
    lateinit var characters : Characters
    lateinit var photoURI: Uri

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters_details)

        try {

            //region $get param

                characters = intent.getSerializableExtra("CHARACTERS") as Characters

            //endregion

            //region $Toolbar

                toolbar = findViewById(R.id.toolbar)

                setSupportActionBar(toolbar)

                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowTitleEnabled(false)

            //endregion

            //region $Find Elements Screen

                textTitle = findViewById(R.id.toolbar_title)
                textTitle.text = characters.name

                textID = findViewById(R.id.tv_id)
                textID.text = characters.id

                textName = findViewById(R.id.tv_name)
                textName.text = characters.name

                textDescription = findViewById(R.id.tv_description)
                textDescription.text = characters.description

                textStories = findViewById(R.id.tv_stories)
                textStories.text = characters.stories?.joinToString(separator = "\n")

                imageCharacter = findViewById(R.id.iv_character_image)
                Picasso.get().load(characters.imageSrc).into(imageCharacter);

            //endregion

        }catch (error: Exception){
            Toast.makeText(application, getString(R.string.project_try_catch_error), Toast.LENGTH_LONG).show()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}