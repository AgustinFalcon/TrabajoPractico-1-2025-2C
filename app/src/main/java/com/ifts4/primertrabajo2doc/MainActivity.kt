package com.ifts4.primertrabajo2doc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.ifts4.primertrabajo2doc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        val editTextName = findViewById<EditText>(R.id.editTextUser)
        val editTextAge = findViewById<EditText>(R.id.editTextPassword)
        val butonLogin = findViewById<Button>(R.id.butonLogin)


        //butonLogin.setOnClickListener{}
        binding.butonLogin.setOnClickListener {
            //val name = editTextName.text.toString()
            //val age = editTextAge.text.toString()

            validateData()
        }

        binding.butonRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        Log.d("CiclosDeVida", "onCreate()")
    }

    override fun onStart() {
        super.onStart()
        Log.d("CiclosDeVida", "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("CiclosDeVida", "onResume()")
        val preferences = getSharedPreferences(RegisterActivity.CREDENCIALES, MODE_PRIVATE)
        val autoLogin = preferences.getBoolean("autoLogin", false)

        Toast.makeText(this, "autoLogin={$autoLogin}", Toast.LENGTH_SHORT).show()
        if (autoLogin == true) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("CiclosDeVida", "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("CiclosDeVida", "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("CiclosDeVida", "onDestroy()")
    }


    fun validateData() {
        val nameBinding = binding.editTextUser.text.toString()
        val ageBinding = binding.editTextPassword.text.toString()

        val preferences = getSharedPreferences(RegisterActivity.CREDENCIALES, MODE_PRIVATE)
        val userInJsonFormat = preferences.getString("userData", null)
        val gson = Gson()

        val user = gson.fromJson(userInJsonFormat, User::class.java)

        try {
            if (nameBinding == user.name && ageBinding == user.password) {
                val checkbox: Boolean = binding.checkboxAutoLogin.isChecked
                val preferences = getSharedPreferences(RegisterActivity.CREDENCIALES, MODE_PRIVATE)
                val edit = preferences.edit()

                edit.putBoolean("autoLogin", checkbox)
                edit.apply()

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show()

            } else {
                // mensaje complete todos los campos
                Toast.makeText(this, "Valores incorrectos!", Toast.LENGTH_LONG).show()
            }

        } catch (e: NullPointerException) {
            Toast.makeText(this, "Primero debes almacenar algun dato!", Toast.LENGTH_LONG).show()
        }

    }

}
