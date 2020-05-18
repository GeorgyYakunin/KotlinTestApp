package com.example.kotlintest

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlintest.Adapter.PostAdapter
import com.example.kotlintest.Model.CompanyInfo
import com.example.kotlintest.Model.CompanyItem
import com.example.kotlintest.Retrofit.MyApi
import com.example.kotlintest.Retrofit.RetrofitClient
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class CompanyInfoActivity : AppCompatActivity(){

    internal lateinit var jsonApi:MyApi
    private val compositeDisposable = CompositeDisposable()
    private var id:String = ""
    internal lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.company_info_activity)
//        rootView = findViewById(R.layout.company_info_activity)

        id= intent.getStringExtra("id")

        Log.e("CompanyInfoActivity", id)
        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(MyApi::class.java)
        fetchData()
    }

    private fun fetchData() {
        compositeDisposable.add(jsonApi.getInfo(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{info -> displayData(info)}
        )

    }

    private fun displayData(info: List<CompanyInfo>?) {
        if(info != null){
            var information:CompanyInfo = info.get(0)
            Picasso.with(this).load("http://megakohz.bget.ru/test_task/" + information.img)
                .placeholder(R.mipmap.ic_launcher_round)// optional
                .error(R.mipmap.ic_launcher)// optional
                .into(findViewById<ImageView>(R.id.company_img))

            findViewById<TextView>(R.id.company_name).text = information.name
            findViewById<TextView>(R.id.company_description).text = information.description
            findViewById<TextView>(R.id.phone_number).text = information.phone
            findViewById<TextView>(R.id.site_link).text = information.www
            findViewById<TextView>(R.id.site_link).text = information.www
            Log.e("Tag", "" + info)
        }

//        ((TextView)findViewById(R.id))
    }
}