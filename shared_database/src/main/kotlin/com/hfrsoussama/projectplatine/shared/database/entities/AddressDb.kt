package com.hfrsoussama.projectplatine.shared.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class AddressDb (

    @ColumnInfo(name = "street")
    val street: String ="",

    @ColumnInfo(name = "suite")
    val suite: String = "",

    @ColumnInfo(name = "city")
    val city: String = "",

    @ColumnInfo(name = "zip_code")
    val zipCode: String = "",

    @Embedded(prefix = "geoloc_")
    val geo: GeoLocalisationDb?

)

data class GeoLocalisationDb (

    @ColumnInfo(name = "latitude")
    val latitude: String,

    @ColumnInfo(name = "longitude")
    val longitude: String
)
