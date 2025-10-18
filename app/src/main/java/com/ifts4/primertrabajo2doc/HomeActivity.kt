package com.ifts4.primertrabajo2doc

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.ifts4.primertrabajo2doc.databinding.ActivityHomeBinding
import com.ifts4.primertrabajo2doc.fragments.FirstFragment
import com.ifts4.primertrabajo2doc.fragments.SecondFragment
import com.ifts4.primertrabajo2doc.fragments.third.ThirdFragment

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        drawerLayout = binding.drawerLayout

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.nav_drawer_home_open,
            R.string.nav_drawer_home_close
        )

        drawerLayout.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_burguer)

        binding.navigationView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            replaceFragment(FirstFragment())
            binding.navigationView.setCheckedItem(R.id.nav_item_one)
        }

        val preferences = getSharedPreferences(RegisterActivity.CREDENCIALES, MODE_PRIVATE)
        /*binding.btnSingOut.setOnClickListener {
            preferences.edit().clear().apply()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }*/

        // Nuevo manejo del botón "Atrás"
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    Toast.makeText(this@HomeActivity, "Acción de retroceso deshabilitada", Toast.LENGTH_SHORT).show()
                    // Si quisieras permitir salir en algún caso, podrías usar:
                    //isEnabled = false
                    // onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }

    private fun replaceFragment(fragment: Fragment) {
        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.fragmentContainer, fragment)
        transition.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_item_one -> {
                replaceFragment(FirstFragment())
                Toast.makeText(this, "Click Opcion 1", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_item_two -> {
                replaceFragment(SecondFragment())
                Toast.makeText(this, "2 Clicked", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_item_three -> {
                replaceFragment(ThirdFragment())
                Toast.makeText(this, "33333333", Toast.LENGTH_SHORT).show()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    /*override fun onBackPressed() {
        // Si el drawer está abierto, solo lo cierra
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            Toast.makeText(this, "Acción de retroceso deshabilitada", Toast.LENGTH_SHORT).show()
        }
    }*/
}
