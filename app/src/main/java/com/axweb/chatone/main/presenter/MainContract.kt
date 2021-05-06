package com.axweb.chatone.main.presenter

import android.view.MenuItem
import androidx.fragment.app.Fragment

interface MainContract  {

    interface View  {
        fun redirectFragment(fragment: Fragment?)
        fun openMenu()
        fun closeMenu()
        fun setTitleToolbar(title: String)
    }

    interface Presenter {

        fun onNavigationClick(menuItem: MenuItem)

    }
}