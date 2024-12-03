package com.example.testcompose101.datamodels

data class QuoteResponse(
    val limit: Int,
    val quotes: List<Quote>,
    val skip: Int,
    val total: Int
)