package com.soora.musicplayerapp.service

import com.soora.musicplayerapp.data.MusicInfo
import retrofit2.Call
import retrofit2.http.GET

interface FloMusicService {

    @GET("2020-flo/song.json")
    fun getMusicInfo(): Call<MusicInfo>
}