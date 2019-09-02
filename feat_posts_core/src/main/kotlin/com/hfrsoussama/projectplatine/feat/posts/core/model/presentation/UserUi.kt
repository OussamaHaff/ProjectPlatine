package com.hfrsoussama.projectplatine.feat.posts.core.model.presentation

data class UserUi(
    val id: Long?,
    val name: String?,
    val username: String?,
    val email: String?,
    val address: AddressUi?,
    val phone: String?,
    val webSite: String?,
    val company: CompanyUi?
)

