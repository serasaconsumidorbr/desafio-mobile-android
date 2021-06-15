package com.luisedu.marvel_app.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.luisedu.marvel_app.R
import com.luisedu.marvel_app.model.Thumbnail

import kotlinx.android.synthetic.main.activity_hero_description.*
import kotlinx.android.synthetic.main.activity_hero_description.ivClose

class HeroDescriptionActivity : AppCompatActivity() {

    private var characterImage: Thumbnail? = null
    private var characterName: String? = ""
    private var characterDescription: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_description)

        getHeroInfo()
        screenSetup()
    }

    private fun screenSetup() {
        setCloseButtonListener()

        tvHeroName.text = characterName
        tvHeroDescription.text = characterDescription
        Glide.with(this)
            .load(characterImage?.path + imageSizeUrl + characterImage?.extension)
            .into(ivHeroDescription)
    }

    private fun getHeroInfo() {
        characterImage = intent.getParcelableExtra<Thumbnail>("characterThumb")
        characterName = intent.getStringExtra("characterName")
        characterDescription = intent.getStringExtra("characterDescription")
    }

    private fun setCloseButtonListener() {
        ivClose.setOnClickListener{
            this.finish()
        }
    }

    companion object {
        const val imageSizeUrl = "/standard_fantastic."
    }

}