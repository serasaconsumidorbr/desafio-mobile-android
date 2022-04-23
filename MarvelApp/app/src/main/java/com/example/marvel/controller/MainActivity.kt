package com.example.marvel.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.marvel.R
import com.squareup.picasso.Picasso
import java.lang.Exception

class MainActivity : AppCompatActivity()  {

    lateinit var toolbar: Toolbar
    lateinit var textTitle: TextView
    lateinit var imageMain : ImageView
    lateinit var buttonCharacters : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {

            //region $Toolbar
            toolbar = findViewById(R.id.toolbar)
            setSupportActionBar(toolbar)

            supportActionBar?.setDisplayShowTitleEnabled(false)
            //endregion

            //region $Find Elements Screen
            textTitle = findViewById(R.id.toolbar_title)
            textTitle.text = getString(R.string.main_toolbar_title)

            imageMain = findViewById(R.id.img_Main)
            imageMain.setImageResource(R.drawable.marvel_universe)

            buttonCharacters = findViewById(R.id.btn_characters)
            buttonCharacters.setOnClickListener {
                val intent = Intent(this, CharactersActivity::class.java)
                startActivity(intent)
            }

            //endregion

        }catch (error: Exception){
            Toast.makeText(application, getString(R.string.project_try_catch_error), Toast.LENGTH_LONG).show()
        }

    }
}