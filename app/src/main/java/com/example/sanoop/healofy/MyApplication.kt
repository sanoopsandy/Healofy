package com.example.sanoop.healofy

import android.app.Application
import com.example.sanoop.healofy.daggerComponents.DaggerNetComponent
import com.example.sanoop.healofy.daggerComponents.NetComponent
import com.example.sanoop.healofy.daggerModules.AppModule
import com.example.sanoop.healofy.daggerModules.NetModule
import com.example.sanoop.healofy.dao.DBHelper
import com.example.sanoop.healofy.utils.Constants

class MyApplication : Application() {

    companion object {
        //platformStatic allow access it from java code
        @JvmStatic
        lateinit var netComponent: NetComponent
    }

    override fun onCreate() {
        super.onCreate()
        DBHelper(this)
        netComponent = DaggerNetComponent.builder()
                .netModule(NetModule(Constants.BASE_URL))
                .build()
    }

}