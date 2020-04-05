package com.s.coronavirus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.s.coronavirus.adapter.OverviewAdapter
import com.s.coronavirus.adapter.entities.OverviewData
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
  private val adapter: OverviewAdapter by lazy { OverviewAdapter() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    initRecyclerView()

    showData(List(10) { OverviewData("countryName", 560, 18300) })

    runBlocking {
      val client = HttpClient(OkHttp) { install(JsonFeature) { serializer = GsonSerializer() } }
      val response =
        client.get<LocationsResponseWrapper>("https://coronavirus-tracker-api.herokuapp.com/v2/locations")
      client.close()
    }

  }

  private fun initRecyclerView() {
    countries_overview_rv.layoutManager =
      LinearLayoutManager(this, RecyclerView.VERTICAL, false)
  }

  private fun showData(data: List<OverviewData>) {
    if (countries_overview_rv.adapter == null)
      countries_overview_rv.adapter = adapter

    adapter.items = data
  }
}
