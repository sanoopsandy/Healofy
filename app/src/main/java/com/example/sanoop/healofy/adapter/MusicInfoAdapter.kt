package com.example.sanoop.healofy.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.sanoop.healofy.BR
import com.example.sanoop.healofy.R
import com.example.sanoop.healofy.models.MusicInfo

class MusicInfoAdapter(private val musicList: List<MusicInfo>) : RecyclerView.Adapter<MusicInfoAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(musicList[position])
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val viewBinding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent?.getContext()), R.layout.row_music_info, parent, false)
        return ViewHolder(viewBinding)
    }

    class ViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(forecastModel: MusicInfo){
            binding.setVariable(BR.musicInfo, forecastModel)
        }
    }

}