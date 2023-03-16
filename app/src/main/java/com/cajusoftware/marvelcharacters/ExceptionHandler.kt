package com.cajusoftware.marvelcharacters

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import com.cajusoftware.marvelcharacters.ui.MainActivity
import com.cajusoftware.marvelcharacters.ui.MainActivity.Companion.ERROR_TAG
import com.cajusoftware.marvelcharacters.ui.components.CarouselItemNotFound
import java.lang.Thread.UncaughtExceptionHandler
import kotlin.system.exitProcess

class ExceptionHandler(private val context: Context) : UncaughtExceptionHandler {

    override fun uncaughtException(t: Thread, e: Throwable) {
        Intent(context, MainActivity::class.java).apply {
            if (e is CarouselItemNotFound) {
                putExtra(ERROR_TAG, e.message)
            } else {
                putExtra(ERROR_TAG, context.getString(R.string.error_not_found))
            }
            addFlags(FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(this)
        }
        android.os.Process.killProcess(android.os.Process.myPid())
        exitProcess(10)
    }
}