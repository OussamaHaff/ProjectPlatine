package com.hfrsoussama.projectplatine.feat.posts.core.model.remote

data class AddressWs(
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipCode: String?,
    val geo: GeoLocalisationWs?
)
