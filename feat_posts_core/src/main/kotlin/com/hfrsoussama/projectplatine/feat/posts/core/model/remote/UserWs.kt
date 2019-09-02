package com.hfrsoussama.projectplatine.feat.posts.core.model.remote

data class UserWs(
    val id: Long?,
    val name: String?,
    val username: String?,
    val email: String?,
    val address: AddressWs?,
    val phone: String?,
    val webSite: String?,
    val company: CompanyWs?
)

