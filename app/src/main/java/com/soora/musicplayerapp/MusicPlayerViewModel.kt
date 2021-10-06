package com.soora.musicplayerapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.soora.musicplayerapp.data.Lyric
import com.soora.musicplayerapp.data.MusicInfo
import com.soora.musicplayerapp.service.FloRetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicPlayerViewModel : ViewModel() {
    private val musicInfo = MutableLiveData<MusicInfo>()
    private val lyrics = MutableLiveData<ArrayList<Lyric>>()

    init {
        getFloMusicInfo()
    }

    fun getMusicInfo(): MutableLiveData<MusicInfo> {
        return musicInfo
    }

    fun getLyrics(): MutableLiveData<ArrayList<Lyric>> {
        return lyrics
    }

    private fun getFloMusicInfo() {
        val call = FloRetrofitClient.musicService

        call.getMusicInfo().enqueue(object : Callback<MusicInfo> {
            override fun onResponse(call: Call<MusicInfo>?, response: Response<MusicInfo>) {
//                val lyricData = (response.body() as MusicInfo).lyrics.split("\n")
//                val list = ArrayList<Lyric>()
//                for (line in lyricData) {
//                    list.add(Lyric(line.substring(0, 10), line.substringAfter("]")))
//                }
//                lyrics.postValue(list)
                musicInfo.postValue(response.body() as MusicInfo)
            }

            override fun onFailure(call: Call<MusicInfo>?, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}