package com.example.astonfinalproject.main.network

import com.example.astonfinalproject.main.core.NetworkProvider
import dagger.Component


@Component(
    modules = [NetworkModule::class]
)
interface NetworkComponent: NetworkProvider {

    companion object{

        fun init(): NetworkComponent {
            return DaggerNetworkComponent.factory().create()
        }
    }

    @Component.Factory
    interface Factory{

        fun create(): NetworkComponent
    }
}

