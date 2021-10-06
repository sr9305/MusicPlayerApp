package com.soora.musicplayerapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.soora.musicplayerapp.data.Lyric
import com.soora.musicplayerapp.data.MusicInfo
import com.soora.musicplayerapp.databinding.ActivityMusicBinding
import com.soora.musicplayerapp.service.FloRetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MusicPlayerActivity : AppCompatActivity() {
    private var _binding: ActivityMusicBinding? = null
    private val binding get() = _binding!!

    private val viewModel = MusicPlayerViewModel()
    private var lyrics = ArrayList<Lyric>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getMusicInfo().observe(this, {
            binding.apply {
                title.text = it.title
                album.text = it.album
                singer.text = it.singer
                Glide.with(applicationContext)
                    .load(it.image)
                    .into(albumImage)
            }
        })
        viewModel.getLyrics().observe(this, {
            binding.lyric.text = it[0].text
            lyrics = it
        })
    }

    companion object {
        private val TAG = "SplashActivity"
    }
}