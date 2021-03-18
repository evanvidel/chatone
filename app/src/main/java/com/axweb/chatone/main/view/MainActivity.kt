package com.axweb.chatone.main.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.axweb.chatone.R
import com.axweb.chatone.databinding.ActivityMainBinding
import com.axweb.chatone.feed.view.FeedFragment
import com.axweb.chatone.main.presenter.MainContract
import com.axweb.chatone.main.presenter.MainPresenter
import com.axweb.chatone.user.perfil.view.PerfilActivity

class MainActivity : AppCompatActivity(), MainContract.View {


    private lateinit var binding: ActivityMainBinding
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            openMenu()
        }

        binding.imgGoToProfile.setOnClickListener {
            startActivity(Intent(this, PerfilActivity::class.java))
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            presenter.onNavigationClick(it)
            true
        }

        setInitFragment()
    }

    private fun setInitFragment() {
        supportFragmentManager.beginTransaction().add(R.id.fragment_content, FeedFragment())
            .commit()
    }

    override fun openMenu() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    override fun closeMeu() {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onDestroy() {
        presenter.view = null
        super.onDestroy()
    }

    override fun redirectFragment(fragment: Fragment?) {
        requireNotNull(fragment)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_content, fragment).commit()
    }


}