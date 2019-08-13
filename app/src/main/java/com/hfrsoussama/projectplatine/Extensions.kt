package com.hfrsoussama.projectplatine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfrsoussama.projectplatine.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.HttpUrl

/**
 * An Extension Function that receives a suspending block (coroutine) and launch it inside via ViewModel'Scope by
 * calling [CoroutineScope.launch] on the Extension Attribute [ViewModel.viewModelScope].
 *
 * @param block the coroutine code which will be invoked in the context of the provided scope.
 */
fun ViewModel.launch(block: suspend CoroutineScope.() -> Unit): Job = this.viewModelScope.launch(block = block)

/**
 * An Extension Function that permits to generate an HTTP URL using the first two letters of the username and the last
 * two letters of the name. This URL is used to download a random avatar image from adorable.io
 *
 * @return an HTTP URL to download the avatar image from adorable.io
 */
fun User.generateAvatarHttpUrl() : HttpUrl {
    return HttpUrl.Builder()
        .scheme("https")
        .host("api.adorable.io")
        .addPathSegment("avatars")
        .addPathSegment("100")
        .addPathSegment(username.take(2).plus(name.takeLast(2)).plus(".png"))
        .build()
}

