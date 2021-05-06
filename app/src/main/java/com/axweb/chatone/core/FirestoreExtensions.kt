package com.axweb.chatone.core

import com.axweb.chatone.posts.model.Post
import com.axweb.chatone.user.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

fun getCurrentUser(callback: (User?) -> Unit) {
    getUser(FirebaseAuth.getInstance().currentUser?.uid, callback)
}


fun getUser(firebaseId: String?, callback: (User?) -> Unit) {
    requireNotNull(firebaseId) { callback(null) }
    FirebaseFirestore.getInstance().collection("users")
        .document(firebaseId)
        .get()
        .addOnSuccessListener {
            callback(it.toObject(User::class.java))
        }
        .addOnFailureListener {
            callback(null)
        }
}

fun User?.newPost(post: Post, callback: (Boolean) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    post.author = this?.firebase_id
    db.collection("posts")
        .add(post)
        .addOnSuccessListener {
            callback(true)
        }.addOnFailureListener {
            it.printStackTrace()
            callback(false)
        }
}

fun User.getListPosts(callback: (ArrayList<Post>) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("posts")
        .whereEqualTo("author", firebase_id)
        .get()
        .addOnSuccessListener {
            callback(ArrayList(it.toObjects(Post::class.java)))
        }.addOnFailureListener {
            callback(arrayListOf())
        }
}