package com.aniketassociates.networking

import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): Result<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful)
                Result.Success(response.body()!!)
            else
                response.getErrorObject()
        } catch (e: IOException) {
            e.printStackTrace()
            Result.Error(
                NO_INTERNET,
                NO_INTERNET_MESSAGE
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(
                SOMETHING_WENT_WRONG,
                SOMETHING_WENT_WRONG_MESSAGE
            )
        }
    }
}

@Throws(Exception::class)
fun <T : Any> Response<T>.getErrorObject(): Result.Error {
    val error = errorBody()?.string()
    val message = StringBuilder()
    error?.let {
        try {

            message.append((JSONObject(it).getJSONObject("error")).get("message"))
            println("message-->: ${message.toString()}")

        } catch (e: JSONException) {
            try {
                println("message-->: ${message.toString()}")

                message.append((JSONObject(it)).get("message"))
            } catch (e: JSONException) {
                e.printStackTrace()
                message.append(SOMETHING_WENT_WRONG_MESSAGE)
            }
        }
    }
    return Result.Error(code(), message.toString())
}

private const val NO_INTERNET = 1
private const val SOMETHING_WENT_WRONG = 2
private const val NO_INTERNET_MESSAGE = "No internet connection"
private const val SOMETHING_WENT_WRONG_MESSAGE = "Something went wrong"