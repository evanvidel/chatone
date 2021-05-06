package com.axweb.chatone.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseMVPFragment<BIND : ViewBinding> : Fragment(), BaseMVPView {

    lateinit var binding: BIND

    abstract fun onCreateView(inflater: LayoutInflater): View


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onCreateView(inflater)
    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }


}