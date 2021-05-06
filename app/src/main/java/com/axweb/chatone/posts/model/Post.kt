package com.axweb.chatone.posts.model

import java.util.*

data class Post(
    val text: String? = null,
    val date: Date? = null,
    val image: String? = null,
    //firebase uid user
    var author: String? = null
)
