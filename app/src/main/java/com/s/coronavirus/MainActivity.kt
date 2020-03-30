package com.s.coronavirus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.s.coronavirus.adapter.OverviewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  private val adapter: OverviewAdapter by lazy { OverviewAdapter() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    initRecyclerView()
    
    showData(List(10) {Unit})
  }

  private fun initRecyclerView() {
    countries_overview_rv.layoutManager =
      LinearLayoutManager(this, RecyclerView.VERTICAL, false)
  }

  private fun showData(data: List<Unit>) {
    if (countries_overview_rv.adapter == null)
      countries_overview_rv.adapter = adapter

    adapter.items = data
  }
}
