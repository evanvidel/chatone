package com.axweb.chatone.posts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.axweb.chatone.databinding.FragmentPostsBinding

class PostsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binder = FragmentPostsBinding.inflate(inflater)
        return binder.root
    }


}