package com.coronavirus.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coronavirus.R
import com.coronavirus.data.LocationsResponseWrapper
import com.coronavirus.data.WebServiceProvider
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

    showData(List(10) { OverviewData("countryName", 560, 18300) })

    runBlocking {
      WebServiceProvider().get<LocationsResponseWrapper>("/v2/locations")
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
