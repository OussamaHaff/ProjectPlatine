package com.hfrsoussama.projectplatine.feat.posts.core.model.extensions

import com.hfrsoussama.projectplatine.feat.posts.core.model.User
import okhttp3.HttpUrl

/**
 * An Extension Function that permits to generate an HTTP URL using the first two letters of the username and the last
 * two letters of the name. This URL is used to download a random avatar image from adorable.io
 *
 * @return an HTTP URL to download the avatar image from adorable.io
 */
fun User.generateAvatarHttpUrl(): HttpUrl {
    return HttpUrl.Builder()
        .scheme("https")
        .host("api.adorable.io")
        .addPathSegment("avatars")
        .addPathSegment("100")
        .addPathSegment(username.take(2).plus(name.takeLast(2)).plus(".png"))
        .build()
}

