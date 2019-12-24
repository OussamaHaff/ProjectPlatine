package com.hfrsoussama.projectplatine.feat.posts.core.model.extensions

import com.hfrsoussama.projectplatine.shared.database.entities.PostDb
import org.junit.Test

import org.junit.Assert.*

class ModelMappersExtensionsKtTest {

    @Test
    fun `test mapping post object from DB model to UI model`() {
        val postDb = PostDb(id = 99L, userId = 999L, title = "post title", body = "post body")
        val postUi = toUiModel(postDb)
        assertTrue(
                    postUi.id == postDb.id &&
                    postUi.userId == postDb.userId &&
                    postUi.title == postDb.title &&
                    postUi.body == postDb.body
        )
    }
}