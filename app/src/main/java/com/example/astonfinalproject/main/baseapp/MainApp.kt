package com.example.astonfinalproject.main.baseapp

import android.app.Application
import com.example.astonfinalproject.main.baseapp.di.AppComponent
import com.example.astonfinalproject.main.core.App


class MainApp : Application(), App {

    private var appComponent : AppComponent? = null

    private fun getAppComponent(): AppComponent {
        if (appComponent == null){
            appComponent = AppComponent.init(applicationContext)
        }
        return appComponent!!
    }

    override fun appComponent(): AppComponent {
        return getAppComponent()
    }
}
