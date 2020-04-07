package com.coronavirus.data.repository.common

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class WebServiceManager {

  val client: HttpClient =
    HttpClient(OkHttp) {
      install(JsonFeature) {
        serializer = KotlinxSerializer(
          Json(JsonConfiguration(ignoreUnknownKeys = true, isLenient = true))
        )
      }
    }

  suspend inline fun <reified T> get(urlContext: String): ServiceResultWrapper<T> {
    val response = safeApiCall { client.get<T>("$BASE_URL$urlContext") }
    client.close()
    return response
  }
}

const val BASE_URL = "https://coronavirus-tracker-api.herokuapp.com"
