package dev.ribeiro.bruno.desafioandroid.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.ribeiro.bruno.desafioandroid.R
import dev.ribeiro.bruno.desafioandroid.databinding.ActivityMainBinding
import dev.ribeiro.bruno.desafioandroid.presentation.ui.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        callFragment()
    }

    private fun callFragment(){
        val fm = supportFragmentManager
        val tr = fm.beginTransaction()
        tr.add(R.id.frame_layout, HomeFragment())
        tr.commitAllowingStateLoss()
    }

}