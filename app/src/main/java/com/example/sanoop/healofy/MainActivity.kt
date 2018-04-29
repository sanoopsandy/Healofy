package com.example.sanoop.healofy

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.sanoop.healofy.adapter.MusicInfoAdapter
import com.example.sanoop.healofy.dao.DBHelper
import com.example.sanoop.healofy.databinding.ActivityMainBinding
import com.example.sanoop.healofy.models.MusicInfo
import com.example.sanoop.healofy.viewModels.MusicListViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MusicListViewModel
    private lateinit var musicList: List<MusicInfo>
    private lateinit var adapter: MusicInfoAdapter
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MusicListViewModel::class.java)
        musicList = ArrayList()
        binding.activity = this
        dbHelper = DBHelper(this)
        adapter = MusicInfoAdapter(musicList)
        binding.rvMusicList.layoutManager = LinearLayoutManager(this)
        binding.rvMusicList.adapter = adapter
        //Set progress
        getMusicListFromCache()
    }

    private fun getMusicListFromCache() {
        val musicList = dbHelper.getMusicList();
        if (musicList.size > 0) {
            adapter = MusicInfoAdapter(musicList)
            binding.rvMusicList.adapter = adapter
        } else {
            observeMusicInfo()
        }
    }

    fun search(view: View) {

        if (binding.edtSearch.text.length > 2) {
            val searchList = dbHelper.searchMusic(binding.edtSearch.text.toString())
            if (searchList.size > 0) {
                adapter = MusicInfoAdapter(searchList)
                binding.rvMusicList.adapter = adapter
            } else {
                Toast.makeText(this, "No results found", Toast.LENGTH_LONG).show()
            }
        } else {
            getMusicListFromCache()
        }

    }

    private fun observeMusicInfo() {
        viewModel.getMusicInfo().observe(this, Observer {
            if (it != null && it.size > 0) {
                musicList = it
                adapter = MusicInfoAdapter(musicList)
                binding.rvMusicList.adapter = adapter
                dbHelper.saveMusicList(it)
            } else {
                Toast.makeText(this, "Error in connection", Toast.LENGTH_LONG).show()
            }
        })
    }
}
