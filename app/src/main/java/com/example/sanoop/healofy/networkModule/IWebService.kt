package com.example.sanoop.healofy.networkModule

import com.example.sanoop.healofy.models.MusicInfo
import retrofit2.Call
import retrofit2.http.GET

interface IWebService {

    @GET("externaltest190/sample_musicdata.json")
    fun getMusicData(): Call<List<MusicInfo>>

}