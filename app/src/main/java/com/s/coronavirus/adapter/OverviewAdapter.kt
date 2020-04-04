package com.s.coronavirus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.s.coronavirus.R
import com.s.coronavirus.adapter.entities.OverviewData

class OverviewAdapter : RecyclerView.Adapter<CountryOverviewViewHolder>() {

  var items: List<OverviewData> = listOf()
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryOverviewViewHolder {
    return CountryOverviewViewHolder(LayoutInflater.from(parent.context)
      .inflate(R.layout.list_item_country_overview, parent, false))
  }

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(holder: CountryOverviewViewHolder, position: Int) {
    holder.bind(items[position])
  }
}
