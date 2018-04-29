package com.example.sanoop.healofy.daggerComponents

import com.example.sanoop.healofy.MainActivity
import com.example.sanoop.healofy.daggerModules.AppModule
import com.example.sanoop.healofy.daggerModules.NetModule
import com.example.sanoop.healofy.networkModule.NetworkDataManager
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class])
interface NetComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(networkDataManager: NetworkDataManager)
}