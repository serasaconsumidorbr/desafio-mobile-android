package br.com.marvelcomics

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import org.koin.core.KoinApplication

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}

class CustomTestRunner: AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader,
        appName: String,
        context: Context
    ) : Application {
        return super.newApplication(
            cl, TestApplication::class.java.name, context)
    }
}