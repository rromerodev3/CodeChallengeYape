package com.example.codechallengeyape.data.network.remote

import com.example.codechallengeyape.data.network.ResultData
import retrofit2.Response
import java.io.IOException

abstract class BaseRemoteDataSource {
    suspend fun <T : Any> safeApiCall(
        errorMessage: String,
        apiCall: suspend () -> Response<T>,
    ): ResultData<T> {
        return try {
            val response = apiCall()

            if (response.isSuccessful)
                ResultData.Success(response.body()!!)
            else {
                val responseErrorMessage = response.errorBody()!!.toString()
                val responseCode = response.code()
                ResultData.Error(
                    responseErrorMessage,
                    IOException("$errorMessage: $responseCode - $responseErrorMessage"),
                    responseCode
                )
            }
        } catch (e: Exception) {
            ResultData.Error(errorMessage, IOException(errorMessage, e))
        }
    }
}