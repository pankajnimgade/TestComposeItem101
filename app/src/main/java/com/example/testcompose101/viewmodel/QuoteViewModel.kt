package com.example.testcompose101.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.testcompose101.datamodels.Quote
import com.example.testcompose101.datamodels.QuoteResponse
import com.example.testcompose101.network.QuoteAPI
import com.example.testcompose101.network.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuoteViewModel : ViewModel() {

    val TAG = "QuoteViewModel"

    private var masterList = mutableStateListOf<Quote>()
    private val quoteList = mutableStateListOf<Quote>()
    var currentList: List<Quote> = quoteList


    fun getQuoteList(): List<Quote> {
        val call = getRetrofit().create(QuoteAPI::class.java).getQuotes()
        call.enqueue(object : Callback<QuoteResponse> {
            override fun onResponse(call: Call<QuoteResponse>, response: Response<QuoteResponse>) {
                response.body()?.let {
                    quoteList.clear()
                    quoteList.addAll(it.quotes)
                    masterList.clear()
                    masterList.addAll(quoteList)
                    Log.d(TAG, "success: $it")
                }
            }

            override fun onFailure(call: Call<QuoteResponse>, t: Throwable) {
                // Good luck
                Log.d(TAG, "Good luck :(  ${t}")

            }

        })
        return currentList
    }

    fun searchWithAuthor(authorName: String) {
        Log.d(TAG, "searchWithAuthor: $authorName")
        val filterWithAuthorList = masterList.filter { quote ->
            quote.author.contains(authorName)
        }
        if (!filterWithAuthorList.isNullOrEmpty()) {
            quoteList.clear()
            quoteList.addAll(filterWithAuthorList)
        }
    }

    fun searchWithQuote(words: String) {
        Log.d(TAG, "searchWithQuote: $words")
        val filterWithQuoteList = masterList.filter { quote ->
            quote.quote.contains(words)
        }
        if (!filterWithQuoteList.isNullOrEmpty()) {
            quoteList.clear()
            quoteList.addAll(filterWithQuoteList)
        }
    }

}