package com.bouraoui.startwars.di

import com.bouraoui.startwars.MainActivity
import com.bouraoui.startwars.ui.fragment.movie.MovieDetailFragment
import com.bouraoui.startwars.ui.fragment.movie.MoviesListFragment
import dagger.Subcomponent

@Subcomponent
interface MovieComponent {

    /**
     * MovieComponent
     */

    // Factory to create instances of MovieComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: MainActivity)
    fun inject(fragment: MoviesListFragment)
    fun inject(fragment: MovieDetailFragment)

}