package com.hfrsoussama.projectplatine.shared.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithPostsListDB (
    @Embedded
    val userDb: UserDb,

    @Relation(
        parentColumn = "user_id",
        entityColumn = "user_writer_id"
    )
    val postsListDb: List<PostDb>
)
