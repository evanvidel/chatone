package com.axweb.chatone.user.model

import com.axweb.chatone.posts.model.Post
import com.google.firebase.firestore.Exclude

data class User(
    var first_name: String? = null,
    var last_name: String? = null,
    var firebase_id: String? = null,
    var email: String? = null,
    var gender: String? = null,
    var birthdate: String? = null,
    var username: String? = null,
    var photo_path: String? = null,
    var completeRegister: Boolean = false,
) {


    @Exclude
    fun getFullName() = "$first_name $last_name"
    fun newPost(post: Post) {

    }
}