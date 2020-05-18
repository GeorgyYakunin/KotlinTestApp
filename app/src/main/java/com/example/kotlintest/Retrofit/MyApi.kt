package com.example.kotlintest.Retrofit

import com.example.kotlintest.Model.CompanyInfo
import com.example.kotlintest.Model.CompanyItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyApi {

    @get:GET("test.php/")
    val posts:Observable<List<CompanyItem>>

    @GET("test.php")
    fun getInfo(@Query("id") id: String):Observable<List<CompanyInfo>>


}