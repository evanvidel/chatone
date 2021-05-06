package com.axweb.chatone.posts.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.axweb.chatone.core.getCurrentUser
import com.axweb.chatone.core.getListPosts
import com.axweb.chatone.databinding.FragmentPostsBinding
import com.axweb.chatone.mvp.BaseMVPFragment
import com.axweb.chatone.posts.presenter.PostContract
import com.axweb.chatone.posts.presenter.PostPresenter
import com.axweb.chatone.core.setTitleToolbar

class PostsFragment : BaseMVPFragment<FragmentPostsBinding>(),PostContract.View {

    private val presenter = PostPresenter(this)

    override fun onDestroy() {
        presenter.view = null
        super.onDestroy()
    }

    override fun onCreateView(inflater: LayoutInflater): View {
        binding = FragmentPostsBinding.inflate(inflater)

        binding.animationPost.visibility = View.VISIBLE

        setTitleToolbar("Posts")
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCurrentUser {
            it?.getListPosts { listPost ->
                val lista = listPost.sortedByDescending {
                    binding.animationPost.visibility = View.GONE
                    it.date
                }
                binding.rv.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context)
                    adapter = PostAdapter(ArrayList(lista), it)

                }
            }
        }
        binding.fab.setOnClickListener {
            startActivity(Intent(context, CreatePostActivity::class.java))
        }

    }

}

