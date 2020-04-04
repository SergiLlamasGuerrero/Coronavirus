package com.s.coronavirus.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.s.coronavirus.adapter.entities.OverviewData
import kotlinx.android.synthetic.main.list_item_country_overview.view.*

class CountryOverviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun bind(item: OverviewData) {
    with(itemView) {
      name_texView.text = item.countryName
      deaths_textView.text = "Deaths: ${item.deaths}"
      confirmed_textView.text = "Confirmed: ${item.confirmed}"
    }
  }
}
