package com.example.codechallengeyape.data.network

sealed class ResultData<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultData<T>()
    data class Error(val errorMessage: String, val exception: Exception, val code: Int = -1) : ResultData<Nothing>()
}