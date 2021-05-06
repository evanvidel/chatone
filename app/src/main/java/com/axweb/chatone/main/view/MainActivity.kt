package com.axweb.chatone.main.view

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.axweb.chatone.R
import com.axweb.chatone.databinding.ActivityMainBinding
import com.axweb.chatone.feed.view.FeedFragment
import com.axweb.chatone.main.presenter.MainContract
import com.axweb.chatone.main.presenter.MainPresenter
import com.axweb.chatone.user.login.view.FormLoginActivity
import com.axweb.chatone.user.perfil.view.*
import com.google.firebase.auth.FirebaseAuth

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

        binding.root.findViewById<TextView>(R.id.btnLogout).setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, FormLoginActivity::class.java))
            finishAffinity()
        }

        binding.root.findViewById<TextView>(R.id.perguntas).setOnClickListener {
            startActivity(Intent(this, FaqActivity::class.java))
            closeMenu()
        }
        binding.root.findViewById<TextView>(R.id.termos).setOnClickListener {
            startActivity(Intent(this,TermosActivity::class.java))
            closeMenu()
        }
        binding.root.findViewById<TextView>(R.id.sobre).setOnClickListener {
            startActivity(Intent(this,SobreNosActivity::class.java))
            closeMenu()
        }
        binding.root.findViewById<TextView>(R.id.configuracoes).setOnClickListener {
            startActivity(Intent(this,ConfigActivity::class.java))
            closeMenu()
        }
        binding.root.findViewById<TextView>(R.id.explore).setOnClickListener {
            startActivity(Intent(this, ExploreActivity::class.java))
            closeMenu()
        }


        setInitFragment()
    }

    private fun setInitFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_content, FeedFragment())
            .commit()
    }

    override fun openMenu() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    override fun closeMenu() {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun setTitleToolbar(title: String) {
        binding.toolbarTitle.text = title
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