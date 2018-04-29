package com.example.sanoop.healofy.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.sanoop.healofy.models.MusicInfo
import com.example.sanoop.healofy.networkModule.NetworkDataManager

class MusicListViewModel(application: Application) : AndroidViewModel(application) {

    fun getMusicInfo(): LiveData<List<MusicInfo>> {
        val networkManager = NetworkDataManager()
        return networkManager.getMusicData()
    }

}