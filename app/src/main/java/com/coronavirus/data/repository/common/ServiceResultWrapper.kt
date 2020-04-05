package com.coronavirus.data.repository.common

import java.lang.Exception

sealed class ServiceResultWrapper<out T> {
  data class Success<out T>(val data: T) : ServiceResultWrapper<T>()
  data class Error(val exception: Exception) : ServiceResultWrapper<Nothing>()
}

suspend fun <T> safeApiCall(call: suspend () -> T): ServiceResultWrapper<T> = try {
  ServiceResultWrapper.Success(call())
} catch (e: Exception) {
  ServiceResultWrapper.Error(e)
}
