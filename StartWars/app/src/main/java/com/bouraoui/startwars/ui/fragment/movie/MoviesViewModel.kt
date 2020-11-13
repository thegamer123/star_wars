package com.bouraoui.startwars.ui.fragment.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bouraoui.startwars.data.model.FilmModel
import com.bouraoui.startwars.data.repository.MoviesRepository
import com.bouraoui.startwars.util.DataState
import dagger.Reusable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@Reusable
class MoviesViewModel   @Inject constructor()  : ViewModel() {

    @Inject
    lateinit var moviesRepository: MoviesRepository

    private val _films = MutableLiveData<DataState<FilmModel?>>()
    val films: LiveData<DataState<FilmModel?>>
        get() = _films


    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetMovies -> {
                    val result = moviesRepository.getMovies().
                        onEach {
                            _films.value = it
                        }.launchIn(viewModelScope)
                }

                MainStateEvent.None -> {
                    // who cares
                }
            }
        }
    }


    sealed class MainStateEvent{

        object GetMovies: MainStateEvent()

        object None: MainStateEvent()
    }

}

