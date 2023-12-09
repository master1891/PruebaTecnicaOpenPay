package com.nels.master.pruebaopenpay.shared

class Cons {
    //referenciando variables sensibles por medio de c++
    companion object {

        fun getUrlBase() = URL_BASE_MOVIE()
        fun getKey() = KEY()
        fun getKeyAccessToken() = ACCESS_TOKEN()

        init {
            System.loadLibrary("pruebaopenpay")
        }
    }
}
external fun URL_BASE_MOVIE():String
external fun KEY():String
external fun ACCESS_TOKEN():String