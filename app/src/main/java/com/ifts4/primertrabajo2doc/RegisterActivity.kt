package com.ifts4.primertrabajo2doc

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.ifts4.primertrabajo2doc.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*val name = intent.getStringExtra("userName")
        val age = intent.getIntExtra("userAge", 0)

        binding.textViewWelcome.text = "Hola $name, $age"*/

        binding.btnRegister.setOnClickListener {
            validateUserData()
        }
    }

    private fun validateUserData() {
        val name = binding.editTextUser.text.toString().trim()
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()

        if (name.isNotBlank() && email.isNotBlank() && password.length >= 3) {
            val preferences = getSharedPreferences(CREDENCIALES, MODE_PRIVATE)
            val edit = preferences.edit()

            val user = User(name = name, email = email, password = password)
            val gson = Gson()

            val userInJsonFormat = gson.toJson(user)
            edit.putString("userData", userInJsonFormat)

            edit.apply()
            goToMainActivity()


        } else {
            Toast.makeText(this, "Completa todos los campos para el registro!", Toast.LENGTH_LONG).show()
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val CREDENCIALES = "CREDENCIALES"
    }
}
