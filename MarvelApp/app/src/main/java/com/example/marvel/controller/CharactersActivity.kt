package com.example.marvel.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.controller.adapter.CharactersCardAdapter
import com.example.marvel.controller.adapter.CharactersListAdapter
import com.example.marvel.controller.callback.Callback
import com.example.marvel.controller.functions.CharactersFunction
import com.example.marvel.model.Characters
import com.example.marvel.services.model.Marvel_v1_Characters
import com.example.marvel.services.repository.MarvelApi
import com.google.gson.Gson
import org.json.JSONObject
import java.lang.Exception

class CharactersActivity: AppCompatActivity(), Callback {

    lateinit var toolbar: Toolbar
    lateinit var textTitle: TextView
    lateinit var recyclerViewCard: RecyclerView
    lateinit var recyclerViewList: RecyclerView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        try{

            //region $Toolbar

                toolbar = findViewById(R.id.toolbar)

                setSupportActionBar(toolbar)

                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowTitleEnabled(false)

            //endregion

            //region $find Elements Screen

                textTitle = findViewById(R.id.toolbar_title)
                textTitle.text = getString(R.string.characters_toolbar_title)

                progressBar = findViewById(R.id.pb_loading_api)
                progressBar.visibility = View.VISIBLE

                recyclerViewCard = findViewById(R.id.rv_characters_card)
                recyclerViewList = findViewById(R.id.rv_characters_list)

            //endregion

            //region $Call Services api

                MarvelApi(getApplication()).getCharacters(this)

            //endregion

        }catch (error: Exception){
            Toast.makeText(application, getString(R.string.project_try_catch_error), Toast.LENGTH_LONG).show()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun itemSelected(character: Characters) {
        val intent = Intent(this, CharactersDetailActivity::class.java)
        intent.putExtra("CHARACTERS", character)
        startActivity(intent)
    }

    override fun onResponseSucessService(response: String) {

        val listItemData = mutableListOf<Characters>()

        try{

            var gson = Gson()
            var data : Marvel_v1_Characters = gson.fromJson(response, Marvel_v1_Characters::class.java)

            if (data?.code == 200){
                data?.data?.results.map { character ->

                    val id = character?.id
                    val name = character?.name
                    val description = character?.description
                    val imageSrc = character?.thumbnail?.path.replace("http://","https://") + "." + character?.thumbnail?.extension
                    val stories:List<String> = character?.stories?.items?.map { it.name }

                    listItemData.add(
                        Characters(id,name,description,imageSrc,stories)
                    )

                }
            }

            if (listItemData.isEmpty()){
                Toast.makeText(application, getString(R.string.characters_message_error_isempty), Toast.LENGTH_LONG).show()
            }else{

                progressBar.visibility = View.INVISIBLE

                val numShowElementsCard:Int = 5

                val adapterCharacterCard = CharactersCardAdapter(listItemData,this, numShowElementsCard)
                val adapterCharacterList = CharactersListAdapter(CharactersFunction().getElementsList(numShowElementsCard, listItemData),this)

                recyclerViewCard.adapter = adapterCharacterCard
                recyclerViewCard.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

                recyclerViewList.adapter = adapterCharacterList
                recyclerViewList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

            }

        }catch (error: Exception){
            Toast.makeText(application, getString(R.string.project_try_catch_error), Toast.LENGTH_LONG).show()
        }
    }



    override fun onResponseErrorService(response: String) {
        Toast.makeText(application, getString(R.string.characters_message_error_return_api) + " ${response}" , Toast.LENGTH_LONG).show()
    }

}