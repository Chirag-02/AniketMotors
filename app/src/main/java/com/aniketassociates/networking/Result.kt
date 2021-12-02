package com.aniketassociates.networking

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val code: Int = 0, val message: String? = null) : Result<Nothing>()
}
