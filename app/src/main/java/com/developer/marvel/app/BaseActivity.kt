package com.developer.marvel.app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class BaseActivity: AppCompatActivity() {

    companion object {

        fun newInstance(context: Context): Intent {
            return Intent(context, BaseActivity::class.java)
        }

    }

}