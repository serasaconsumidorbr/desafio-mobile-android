package welias.marvel.presentation.ui.activity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import welias.marvel.R
import welias.marvel.databinding.ActivityMainBinding
import welias.marvel.presentation.ui.fragments.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        goToHome()
    }

    private fun goToHome() {
        supportFragmentManager.commit(true) {
            replace(
                viewBinding.container.id,
                HomeFragment.newInstance(),
                HomeFragment::class.java.name
            )
        }
    }
}
