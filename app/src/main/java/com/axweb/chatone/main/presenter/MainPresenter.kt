package com.axweb.chatone.main.presenter

import android.view.MenuItem
import com.axweb.chatone.R
import com.axweb.chatone.contacts.view.ContactsFragment
import com.axweb.chatone.feed.view.FeedFragment
import com.axweb.chatone.posts.view.PostsFragment

class MainPresenter(var view: MainContract.View?) : MainContract.Presenter {


    override fun onNavigationClick(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.action_feed -> view?.redirectFragment(FeedFragment())
            R.id.action_post -> view?.redirectFragment(PostsFragment())
            R.id.action_contacts -> view?.redirectFragment(ContactsFragment())
        }
    }

}