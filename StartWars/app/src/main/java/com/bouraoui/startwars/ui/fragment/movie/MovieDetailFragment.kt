package com.bouraoui.startwars.ui.fragment.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bouraoui.startwars.MyApplication
import com.bouraoui.startwars.R
import com.bouraoui.startwars.data.model.FilmItemModel
import com.bouraoui.startwars.data.model.FilmModel
import com.bouraoui.startwars.databinding.FragmentMovieDetailBinding
import com.bouraoui.startwars.util.DataState
import com.bouraoui.startwars.util.TimestampConverter
import com.bouraoui.startwars.util.TimestampConverter.getStringTimeStampWithDate
import com.google.gson.Gson
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDetailFragment : Fragment() {

    private var movieDetailBinding: FragmentMovieDetailBinding? = null
    private val args: MovieDetailFragmentArgs by navArgs()

    @Inject
    lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as MyApplication).appComponent.movieComponent().create()
            .inject(this)
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
        //bindData()
        val model = Gson().fromJson(args.model, FilmItemModel::class.java)
        initContent()
        val movieId = model.url!!.split("/")[5]
        movieDetailBinding!!.titleTV.text = model.title
        moviesViewModel.setStateEvent(MoviesViewModel.MainStateEvent.GetMovieById,movieId)
    }

    /**
     * I used 2 methods , the first , i passed the data using as a Json model and the second
     * i called the API to get the movie by Id
     */
    private fun bindData() {

        val model = Gson().fromJson(args.model, FilmItemModel::class.java)
        movieDetailBinding!!.titleTV.text = model.title
        movieDetailBinding!!.descriptionTV.text = model.opening_crawl?.replace("\n", "")
        movieDetailBinding!!.dirNameTV.text = model.director
        movieDetailBinding!!.prodNameTV.text = model.producer
        movieDetailBinding!!.dateTV.text = model.release_date?.replace("-", "/")
        movieDetailBinding!!.createdDateTV.text = model.created?.getStringTimeStampWithDate()
        movieDetailBinding!!.editedDateTV.text = model.edited?.getStringTimeStampWithDate()

    }

    private fun bindData(model : FilmItemModel) {

        movieDetailBinding!!.titleTV.text = model.title
        movieDetailBinding!!.descriptionTV.text = model.opening_crawl?.replace("\n", "")
        movieDetailBinding!!.dirNameTV.text = model.director
        movieDetailBinding!!.prodNameTV.text = model.producer
        movieDetailBinding!!.dateTV.text = model.release_date?.replace("-", "/")
        movieDetailBinding!!.createdDateTV.text = model.created?.getStringTimeStampWithDate()
        movieDetailBinding!!.editedDateTV.text = model.edited?.getStringTimeStampWithDate()

    }


    private fun initContent() {

        moviesViewModel.filmsByID.observe(requireActivity(), { dataState ->
            when (dataState) {
                is DataState.Success<FilmItemModel?> -> {
                    movieDetailBinding!!.shimmerLayout.stopShimmer()
                    movieDetailBinding!!.shimmerLayout.hideShimmer()
                    bindData(dataState.data!!)
                }
                is DataState.Error -> {
                    Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading -> {
                    movieDetailBinding!!.shimmerLayout.startShimmer()
                    Log.d("status", "loading")
                }
            }
        })
    }

}