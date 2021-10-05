package com.soora.musicplayerapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.soora.musicplayerapp.data.MusicInfo
import com.soora.musicplayerapp.databinding.ActivityMusicBinding
import com.soora.musicplayerapp.service.FloRetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MusicActivity : AppCompatActivity() {
    private var _binding: ActivityMusicBinding? = null
    private val binding get() = _binding!!

    val musicInfo = MutableLiveData<MusicInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getFloMusicInfo().let {
            Log.d(TAG, it.value?.title.toString())
        }
    }
    fun getFloMusicInfo(): MutableLiveData<MusicInfo> {
        val call = FloRetrofitClient.musicService

        call.getMusicInfo().enqueue(object : Callback<MusicInfo> {
            override fun onResponse(call: Call<MusicInfo>?, response: Response<MusicInfo>) {
                musicInfo.value = response.body() as MusicInfo
            }

            override fun onFailure(call: Call<MusicInfo>?, t: Throwable) {
                t.printStackTrace()
            }
        })
        return musicInfo
    }
    companion object{
        private val TAG = "SplashActivity"
    }
}