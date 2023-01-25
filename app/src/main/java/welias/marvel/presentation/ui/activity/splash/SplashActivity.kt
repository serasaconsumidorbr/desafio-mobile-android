package welias.marvel.presentation.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import welias.marvel.databinding.ActivitySplashBinding
import welias.marvel.presentation.ui.activity.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        navigateToHome()
    }

    private fun navigateToHome() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(
                Intent(baseContext, MainActivity::class.java)
            )
        }, OPEN_DELAY)
    }

    companion object {
        private const val OPEN_DELAY = 2500L
    }
}
