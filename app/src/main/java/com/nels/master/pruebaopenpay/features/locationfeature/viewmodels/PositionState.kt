package com.nels.master.pruebaopenpay.features.locationfeature.viewmodels

import com.nels.master.pruebaopenpay.features.listfeature.viewmodels.states.MoviesStates
import com.nels.master.pruebaopenpay.features.locationfeature.domain.models.Posicion

data class PositionState(
    val positions: List<Posicion> =  emptyList(),
    val status: MainViewMoldel.PositionEvent = MainViewMoldel.PositionEvent.Init
)