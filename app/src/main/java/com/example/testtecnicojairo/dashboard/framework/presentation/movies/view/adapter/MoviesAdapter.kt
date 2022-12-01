package com.example.testtecnicojairo.dashboard.framework.presentation.movies.view.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.example.material.base.BaseAdapter
import com.example.testtecnicojairo.R
import com.example.testtecnicojairo.dashboard.constants.DashboardConstants
import com.example.testtecnicojairo.dashboard.domain.models.Movie
import com.example.testtecnicojairo.dashboard.framework.presentation.movies.view.holder.MoviesHolder

class MoviesAdapter : BaseAdapter<Movie, MoviesHolder>() {

    override fun getRowViewResourceId(viewType: Int): Int = R.layout.item_card_movie

    override fun createHolder(view: View, viewType: Int): MoviesHolder = MoviesHolder(view)

    override fun bindViewHolder(holder: MoviesHolder, item: Movie) {
        holder.apply {
            title.text = item.title
            year.text = item.release_date
            overview.text = item.overview
            Glide.with(coverage).load(
                DashboardConstants.IMG_BASE_URL.plus(item.poster_path)
            ).centerCrop().into(coverage)
        }
    }

    override fun getItemCount(): Int = dataSet?.size ?: 0

}