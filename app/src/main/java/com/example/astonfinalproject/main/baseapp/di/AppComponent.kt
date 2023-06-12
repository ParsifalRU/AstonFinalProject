package com.example.astonfinalproject.main.baseapp.di

import android.content.Context
import com.example.astonfinalproject.main.core.DependenciesProvider
import com.example.astonfinalproject.main.core.NetworkProvider
import com.example.astonfinalproject.main.network.NetworkComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [NetworkProvider::class]
)
interface AppComponent : DependenciesProvider {

    companion object{

        fun init(context: Context): AppComponent {
            val networkComponent = NetworkComponent.init()
            return DaggerAppComponent.factory()
                .create(
                    networkComponent,
                    context
                )
        }
    }

    @Component.Factory
    interface Factory{

        fun create(
            networkProvider: NetworkProvider,
            @BindsInstance context: Context
        ): AppComponent
    }
}