package com.hfrsoussama.projectplatine.model

data class User(
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val webSite: String = "",
    val company: Company
)

