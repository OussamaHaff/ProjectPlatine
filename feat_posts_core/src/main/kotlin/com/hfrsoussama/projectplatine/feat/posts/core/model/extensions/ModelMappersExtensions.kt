package com.hfrsoussama.projectplatine.feat.posts.core.model.extensions

import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.AddressUi
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.CompanyUi
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.GeoLocalisationUi
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.PostUi
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.UserUi
import com.hfrsoussama.projectplatine.feat.posts.core.model.presentation.CommentUi
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.AddressWs
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.GeoLocalisationWs
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.UserWs
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.PostWs
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.CompanyWs
import com.hfrsoussama.projectplatine.feat.posts.core.model.remote.CommentWs
import com.hfrsoussama.projectplatine.shared.database.entities.PostDb


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

fun PostWs.toDbModel() = PostDb(id, userId, title, body)

fun toUiModel(postDb: PostDb) = PostUi(
    id = postDb.id,
    userId = postDb.userId,
    title = postDb.title,
    body = postDb.body
)