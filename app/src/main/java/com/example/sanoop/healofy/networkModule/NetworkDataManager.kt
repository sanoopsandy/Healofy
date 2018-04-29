package com.example.sanoop.healofy.networkModule

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.sanoop.healofy.MyApplication
import com.example.sanoop.healofy.models.MusicInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NetworkDataManager {

    @Inject
    lateinit var iWebService: IWebService

    fun getMusicData(): LiveData<List<MusicInfo>> {
        MyApplication.netComponent.inject(this)
        val data = MutableLiveData<List<MusicInfo>>()
        iWebService.getMusicData().enqueue(object : Callback<List<MusicInfo>> {
            override fun onFailure(call: Call<List<MusicInfo>>?, t: Throwable?) {
                t?.printStackTrace()
                data.value = null
            }

            override fun onResponse(call: Call<List<MusicInfo>>?, response: Response<List<MusicInfo>>?) {

                val musicList = response?.body()
                data.value = musicList

            }


        })

        return data
    }

}