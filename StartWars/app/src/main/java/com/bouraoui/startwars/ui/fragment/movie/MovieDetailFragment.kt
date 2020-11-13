package com.bouraoui.startwars.ui.fragment.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bouraoui.startwars.R
import com.bouraoui.startwars.data.model.FilmItemModel
import com.bouraoui.startwars.databinding.FragmentMovieDetailBinding
import com.bouraoui.startwars.util.TimestampConverter
import com.bouraoui.startwars.util.TimestampConverter.getStringTimeStampWithDate
import com.google.gson.Gson


/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDetailFragment : Fragment() {

    private var movieDetailBinding: FragmentMovieDetailBinding? = null
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        movieDetailBinding = FragmentMovieDetailBinding.inflate(inflater)
        return movieDetailBinding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindData()
    }

    private fun bindData() {
        val model = Gson().fromJson(args.model, FilmItemModel::class.java)
        movieDetailBinding!!.titleTV.text = model.title
        movieDetailBinding!!.descriptionTV.text = model.opening_crawl?.replace("\n", "")
        movieDetailBinding!!.dirNameTV.text = model.director
        movieDetailBinding!!.prodNameTV.text = model.producer
        movieDetailBinding!!.dateTV.text = model.release_date
        movieDetailBinding!!.createdDateTV.text = model.created?.getStringTimeStampWithDate()
        movieDetailBinding!!.editedDateTV.text = model.edited?.getStringTimeStampWithDate()

    }

}