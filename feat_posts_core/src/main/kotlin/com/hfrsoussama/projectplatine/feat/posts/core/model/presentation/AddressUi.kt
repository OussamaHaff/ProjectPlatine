package com.hfrsoussama.projectplatine.feat.posts.core.model.presentation

data class AddressUi(
    val street: String,
    val suite: String,
    val city: String,
    val zipCode: String = "",
    val geo: GeoLocalisationUi
)
