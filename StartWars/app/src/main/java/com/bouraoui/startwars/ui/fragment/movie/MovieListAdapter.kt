package com.bouraoui.startwars.ui.fragment.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bouraoui.startwars.R
import com.bouraoui.startwars.data.model.FilmItemModel
import com.bouraoui.startwars.databinding.MovieItemRowBinding
import javax.inject.Inject


class MovieListAdapter @Inject constructor(
    val context: Context,
    val itemList: MutableList<FilmItemModel>?,
    val callback: (Int) -> Unit
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieItemRowBinding.inflate(layoutInflater)
        return ViewHolder(context, binding)
    }

    override fun getItemCount(): Int {
        return itemList!!.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(itemList!![position], position)
    }

    inner class ViewHolder(
        var context: Context,
        var binding: MovieItemRowBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(order: FilmItemModel, position: Int) {
            binding.titleTV.text = order.title
            binding.dateTV.text = order.release_date?.replace("-", "/")
            binding.dirNameTV.text = order.director
            binding.prodNameTV.text = order.producer
            binding.descriptionTV.text = order.opening_crawl?.replace("\n", "")
            binding.root.setOnClickListener {
                callback.invoke(position)
            }

        }
    }

}