package com.nels.master.pruebaopenpay.features.listfeature.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nels.master.pruebaopenpay.features.listfeature.domain.TopRatedMoviesRepository
import com.nels.master.pruebaopenpay.features.listfeature.domain.usecase.GetAllMoviesUseCase
import com.nels.master.pruebaopenpay.features.listfeature.domain.usecase.GetPopularMoviesUseCase
import com.nels.master.pruebaopenpay.features.listfeature.domain.usecase.GetRecomendationsUseCase
import com.nels.master.pruebaopenpay.features.listfeature.domain.usecase.GetTopRatedMoviesUseCase
import com.nels.master.pruebaopenpay.features.listfeature.viewmodels.states.MoviesStates
import com.nels.master.pruebaopenpay.features.modeoffline.domian.usecases.GetAllMoviesDbUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListMoviesViewModel @Inject constructor(
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
    private val popularMoviesUseCase: GetPopularMoviesUseCase,
    private val topRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val recomendationsUseCase: GetRecomendationsUseCase,

) :ViewModel() {

    var allMoviesState by mutableStateOf(MoviesStates())
        private set
    var popularMoviesState by mutableStateOf(MoviesStates())
        private set
    var topRatedMoviesState by mutableStateOf(MoviesStates())
        private set
    var recomendationsState by mutableStateOf(MoviesStates())
        private set

    fun getAllMovies(){
        viewModelScope.launch {
            when(val result = getAllMoviesUseCase()){
                is GetAllMoviesUseCase.Result.Error -> {
                    allMoviesState = allMoviesState.copy(
                        message = result.mesage,
                        status = MoviesStates.Status.Failure
                    )
                }
                is GetAllMoviesUseCase.Result.Success -> {
                    allMoviesState = allMoviesState.copy(
                        movies = result.moviesResponse.results,
                        status = MoviesStates.Status.Success
                    )
                }
            }
        }
    }

    fun getPopularMovies(){
        viewModelScope.launch {
            when(val result = popularMoviesUseCase()){
                is GetPopularMoviesUseCase.Result.Error -> {
                    popularMoviesState = popularMoviesState.copy(
                        message = result.mesage,
                        status = MoviesStates.Status.Failure
                    )
                }
                is GetPopularMoviesUseCase.Result.Success -> {
                    popularMoviesState = popularMoviesState.copy(
                        movies = result.moviesResponse.results,
                        status = MoviesStates.Status.Success
                    )
                }
            }
        }
    }

    fun getTopRatedMovies(){
        viewModelScope.launch {
            when(val result = topRatedMoviesUseCase()){
                is GetTopRatedMoviesUseCase.Result.Error -> {
                    topRatedMoviesState = topRatedMoviesState.copy(
                        message = result.mesage,
                        status = MoviesStates.Status.Failure
                    )
                }
                is GetTopRatedMoviesUseCase.Result.Success -> {
                    topRatedMoviesState = topRatedMoviesState.copy(
                        movies = result.moviesResponse.results,
                        status = MoviesStates.Status.Success
                    )
                }
            }
        }
    }

    fun getRecomendations(id_movie:Int){
        viewModelScope.launch {
            when(val result = recomendationsUseCase(id_movie)){
                is GetRecomendationsUseCase.Result.Error -> {
                    recomendationsState = recomendationsState.copy(
                        message = result.mesage,
                        status = MoviesStates.Status.Failure
                    )
                }
                is GetRecomendationsUseCase.Result.Success -> {
                    recomendationsState = recomendationsState.copy(
                        movies = result.moviesResponse.results,
                        status = MoviesStates.Status.Success
                    )
                }
            }
        }
    }

}