package com.soora.musicplayerapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FloRetrofitClient {
    private val retrofitClient: Retrofit.Builder by lazy{
        Retrofit.Builder().
                baseUrl(FloMusicApi.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
    }
    val musicService: FloMusicService by lazy{
        retrofitClient.build().create(FloMusicService::class.java)
    }
}