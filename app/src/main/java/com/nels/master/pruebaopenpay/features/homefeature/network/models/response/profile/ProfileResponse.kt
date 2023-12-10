package com.nels.master.pruebaopenpay.features.homefeature.network.models.response.profile

data class ProfileResponse(
    val avatar: Avatar,
    val id: Int,
    val include_adult: Boolean,
    val iso_3166_1: String,
    val iso_639_1: String,
    val name: String,
    val username: String
)