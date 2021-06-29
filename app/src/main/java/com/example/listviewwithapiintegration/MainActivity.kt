package com.example.listviewwithapiintegration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var listView: ListView = findViewById(R.id.listView)

        var rf: Retrofit = Retrofit.Builder()
            .baseUrl(RetrofitInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        var API:RetrofitInterface = rf.create(RetrofitInterface::class.java)
        var call:Call<List<PostModel?>?>? = API.posts

        call?.enqueue(object :Callback<List<PostModel?>?>{
            override fun onResponse(
                call: Call<List<PostModel?>?>,
                response: Response<List<PostModel?>?>
            ) {
                var postList : List<PostModel> = response.body() as List<PostModel>
                var posts: Array<String?> = arrayOfNulls<String>(postList!!.size)


                for (i in postList.indices)
                    posts[i] = postList[i].title

                var adapter = ArrayAdapter<String>(applicationContext,android.R.layout.simple_dropdown_item_1line,posts)
                listView.adapter = adapter
            }

            override fun onFailure(call: Call<List<PostModel?>?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}