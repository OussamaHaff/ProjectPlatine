package com.hfrsoussama.projectplatine.feat.posts.ui.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * An Extension Function that receives a suspending block (coroutine) and launch it inside via ViewModel'Scope by
 * calling [CoroutineScope.launch] on the Extension Attribute [ViewModel.viewModelScope].
 *
 * @param block the coroutine code which will be invoked in the context of the provided scope.
 */
fun ViewModel.launch(block: suspend CoroutineScope.() -> Unit): Job = this.viewModelScope.launch(block = block)
