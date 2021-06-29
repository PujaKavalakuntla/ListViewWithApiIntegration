package com.example.listviewwithapiintegration

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {
    @get:GET(value="posts")
    val posts : Call<List<PostModel?>?>
    companion object{
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }
}