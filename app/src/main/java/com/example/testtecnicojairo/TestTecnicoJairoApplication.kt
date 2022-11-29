package com.example.testtecnicojairo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TestTecnicoJairoApplication : Application() {

    companion object {
        lateinit var instance: TestTecnicoJairoApplication
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}