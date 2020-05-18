package com.example.kotlintest.Retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var ourInterface: Retrofit?=null

    val instance:Retrofit
    get() {
        if (ourInterface == null){
            ourInterface = Retrofit.Builder()
                .baseUrl("http://megakohz.bget.ru/test_task/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return ourInterface!!
    }
}