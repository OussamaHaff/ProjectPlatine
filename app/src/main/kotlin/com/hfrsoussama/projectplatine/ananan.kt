package com.hfrsoussama.projectplatine

import com.hfrsoussama.projectplatine.feat.posts.core.network.PostsRepository
import org.koin.android.ext.android.inject

class Somethin{

    val postsRepository : PostsRepository by inject()

}

