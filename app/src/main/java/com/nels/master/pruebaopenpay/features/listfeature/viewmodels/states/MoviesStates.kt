package com.nels.master.pruebaopenpay.features.listfeature.viewmodels.states

import com.nels.master.pruebaopenpay.features.listfeature.domain.modelos.Movie

data class MoviesStates(
    val movies:List<Movie> = emptyList(),
    val loading:Boolean = false,
    val status: Status = Status.Init,
    val message: String = ""
) {


    sealed class Status{
        object Failure: Status()
        object Success: Status()
        object Init: Status()
    }

}