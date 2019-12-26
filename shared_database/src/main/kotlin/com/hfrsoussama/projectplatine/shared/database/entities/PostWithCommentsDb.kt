package com.hfrsoussama.projectplatine.shared.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class PostWithCommentsDb(
    @Embedded
    val postDb: PostDb,

    @Relation(
        parentColumn = "post_id",
        entityColumn = "parent_post_id"
    )
    val commentsDbList: List<CommentDb>
)
