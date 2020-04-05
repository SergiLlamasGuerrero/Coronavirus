package com.coronavirus.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coronavirus.R
import com.coronavirus.data.repository.common.ServiceResultWrapper
import com.coronavirus.data.repository.common.WebServiceManager
import com.coronavirus.data.repository.entities.CoronavirusRemoteData
import com.coronavirus.ui.adapter.OverviewAdapter
import com.coronavirus.ui.adapter.entities.OverviewData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
  private val adapter: OverviewAdapter by lazy { OverviewAdapter() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    initRecyclerView()

    runBlocking { showData(getData()) }
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

  private suspend fun getData(): List<OverviewData> =
    when (val result = WebServiceManager().get<CoronavirusRemoteData>("/v2/locations")) {
      is ServiceResultWrapper.Success -> {
        result.data.locations.map {
          OverviewData(
            it.country ?: "",
            it.latest.deaths ?: -1,
            it.latest.confirmed ?: -1
          )
        }
      }
      is ServiceResultWrapper.Error -> listOf()
    }
}
