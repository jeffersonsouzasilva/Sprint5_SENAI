package com.example.vsconnect_kotlin.apis

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    companion object{
        fun obterInstanciaRetrofit(url:String = "http://172.16.27.108:8099/") : Retrofit{
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}