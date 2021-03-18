package com.axweb.chatone.feed.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.axweb.chatone.databinding.FragmentFeedBinding
import com.axweb.chatone.feed.presenter.FeedContract
import com.axweb.chatone.feed.presenter.FeedPresenter
import com.axweb.chatone.mvp.BaseMVPFragment

class FeedFragment : BaseMVPFragment<FragmentFeedBinding>(), FeedContract.View {


    private val presenter = FeedPresenter(this)

    override fun onCreateView(inflater: LayoutInflater): View {
        binding = FragmentFeedBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("FeedFragment", "onViewCreated()")

    }

    override fun onDestroy() {
        presenter.view = null
        super.onDestroy()
    }

}