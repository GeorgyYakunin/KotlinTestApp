package com.example.kotlintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintest.Adapter.PostAdapter
import com.example.kotlintest.Model.CompanyItem
import com.example.kotlintest.Retrofit.MyApi
import com.example.kotlintest.Retrofit.RetrofitClient
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.disposables.ArrayCompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var jsonApi:MyApi
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Init Api
        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(MyApi::class.java)

        //View
        recycler_posts.setHasFixedSize(true)
        recycler_posts.layoutManager = LinearLayoutManager(this)
        fetchData()
    }

    private fun fetchData() {
        compositeDisposable.add(jsonApi.posts
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{posts -> displayData(posts)}
        )

    }

    private fun displayData(posts: List<CompanyItem>?) {
        val adapter = PostAdapter(this, posts!!)
        recycler_posts.adapter = adapter

    }
}

