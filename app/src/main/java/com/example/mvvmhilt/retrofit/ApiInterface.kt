package com.example.mvvmhilt.retrofit

import com.example.mvvmhilt.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("b/6766579fad19ca34f8deab20?meta=false")
    suspend fun getProducts():Response<List<Product>>
}