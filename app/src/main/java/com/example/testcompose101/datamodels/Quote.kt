package com.example.testcompose101.datamodels

data class Quote(
    val author: String,
    val id: Int,
    val quote: String
)

fun getDefaultQuote() =
    Quote(author = "Rob Thomas", id = 1, quote = "Something to be")
