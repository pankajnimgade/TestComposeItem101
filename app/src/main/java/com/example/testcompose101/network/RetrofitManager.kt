package com.example.testcompose101.network

import com.example.testcompose101.datamodels.QuoteResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * https://dummyjson.com/quotes
 */

fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

interface QuoteAPI {

    @GET("quotes")
    fun getQuotes(): Call<QuoteResponse>

}