package com.example.testtecnicojairo.dashboard.framework.presentation.movies.view.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.material.base.BaseHolder
import com.example.testtecnicojairo.R

class MoviesHolder(item: View) : BaseHolder(item) {
    val title: TextView = item.findViewById(R.id.title)
    val year: TextView = item.findViewById(R.id.year)
    val overview: TextView = item.findViewById(R.id.overview)
    val coverage: ImageView = item.findViewById(R.id.img_coverage)
}