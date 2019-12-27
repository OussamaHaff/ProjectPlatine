package com.hfrsoussama.projectplatine.feat.posts.core.model.presentation

data class UserUi(
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressUi? = null,
    val phone: String? = null,
    val webSite: String? = null,
    val company: CompanyUi? = null
)

