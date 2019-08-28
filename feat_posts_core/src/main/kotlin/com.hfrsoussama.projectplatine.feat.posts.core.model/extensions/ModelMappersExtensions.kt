package com.hfrsoussama.projectplatine.feat.posts.core.model.extensions

import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.*
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.*


fun AddressWs.toUiModel() = AddressUi(
    street.orEmpty(),
    suite.orEmpty(),
    city.orEmpty(),
    zipCode.orEmpty(),
    geo.toUiModel()
)

fun GeoLocalisationWs?.toUiModel(): GeoLocalisationUi {
    return when (this) {
        null -> GeoLocalisationUi("", "")
        else -> GeoLocalisationUi(lat.orEmpty(), lng.orEmpty())
    }
}

fun CompanyWs?.toUiModel() : CompanyUi {
    return when (this) {
        null -> CompanyUi("", "", "")
        else -> CompanyUi(name.orEmpty(), catchPhrase.orEmpty(), bs.orEmpty())
    }


}


fun UserWs.toUiModel() = UserUi(
    checkNotNull(id),
    name.orEmpty(),
    username.orEmpty(),
    email.orEmpty(),
    address?.toUiModel(),
    phone.orEmpty(),
    webSite.orEmpty(),
    company?.toUiModel()
)

fun PostWs.toUiModel()
        = PostUi(userId, id, title, body)

fun CommentWs.toUiModel()
        = CommentUi(postId, id, name, email, body)

