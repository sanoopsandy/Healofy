package com.example.sanoop.healofy.dao

import com.example.sanoop.healofy.models.MusicInfo

interface IMusicCacheDAO {

    fun saveMusicList(musicList: List<MusicInfo>)
    fun getMusicList(): List<MusicInfo>
    fun searchMusic(query: String): List<MusicInfo>

}