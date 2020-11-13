package com.bouraoui.startwars.ui.fragment.movie

import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bouraoui.startwars.MyApplication
import com.bouraoui.startwars.R
import com.bouraoui.startwars.data.model.FilmItemModel
import com.bouraoui.startwars.data.model.FilmModel
import com.bouraoui.startwars.databinding.FragmentMoviesListBinding
import com.bouraoui.startwars.databinding.FragmentSplashScreenBinding
import com.bouraoui.startwars.util.DataState
import com.google.gson.Gson
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [MoviesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoviesListFragment : Fragment() {

    @Inject
    lateinit var moviesViewModel: MoviesViewModel

    private var binding: FragmentMoviesListBinding? = null

    companion object {
        var lastScrollPosition =0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as MyApplication).appComponent.movieComponent().create()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
            binding = FragmentMoviesListBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadAnimation()
        initContent()
        moviesViewModel.setStateEvent(MoviesViewModel.MainStateEvent.GetMovies)
    }


    private fun initContent() {

        moviesViewModel.films.observe(requireActivity(), { dataState ->
            when (dataState) {
                is DataState.Success<FilmModel?> -> {
                    binding?.loaderIV?.visibility = View.GONE
                    binding?.moviesCountTV?.text = getString(R.string.count, dataState.data?.count)
                    binding?.topBadgeCL?.visibility = View.VISIBLE
                    bindData(dataState.data?.results)
                }
                is DataState.Error -> {
                    Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading -> {
                    Log.d("status", "loading")
                    binding?.loaderIV?.visibility = View.VISIBLE
                    binding?.topBadgeCL?.visibility = View.GONE
                    binding?.dataRV?.visibility = View.GONE
                }
            }
        })
    }


    private fun loadAnimation() {
        val rocketAnimation = binding?.loaderIV?.drawable as Animatable
        rocketAnimation.start()
    }

    private fun bindData(results: MutableList<FilmItemModel>?) {
        binding?.dataRV?.visibility = View.VISIBLE
        binding?.dataRV?.layoutManager = LinearLayoutManager(context)
        val adapter =
            MovieListAdapter(
                requireContext(),
                results
            ) { position ->
                lastScrollPosition = position
                val json = Gson().toJson(results!![position])
                val action =
                    MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailFragment(json,results[position].title!!)
                findNavController().navigate(action)
            }

        binding?.dataRV?.adapter = adapter
        binding?.dataRV?.scrollToPosition(lastScrollPosition)
    }

}