package com.coronavirus.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get

class WebServiceProvider {

  val client: HttpClient =
    HttpClient(OkHttp) {
      install(JsonFeature) {
        serializer = GsonSerializer()
      }
    }

  suspend inline fun <reified T> get(urlContext: String): T {
    val response = client.get<T>("$BASE_URL$urlContext")
    client.close()
    return response
  }
}

const val BASE_URL = "https://coronavirus-tracker-api.herokuapp.com"
