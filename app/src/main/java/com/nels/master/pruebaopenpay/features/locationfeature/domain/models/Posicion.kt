package com.nels.master.pruebaopenpay.features.locationfeature.domain.models

data class Posicion(
    val latitud:Double,
    val longitud:Double,
    val fecha:String
){
   fun convertToMap():Map<String,Any?>{
       return mapOf(
           "latitud" to latitud,
           "longitud" to longitud,
           "fecha" to fecha
       )
    }
}