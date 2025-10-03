package com.ifts4.primertrabajo2doc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.ifts4.primertrabajo2doc.databinding.ActivityRegisterBinding
import com.ifts4.primertrabajo2doc.ejercicio1.Auto

class RegisterActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityRegisterBinding
    val arrayColors: Array<Colors> = Colors.values()
    var colorSelected: Colors? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*val name = intent.getStringExtra("userName")
        val age = intent.getIntExtra("userAge", 0)

        binding.textViewWelcome.text = "Hola $name, $age"*/

        val adapter = ArrayAdapter(this, R.layout.my_simple_spinner_item, arrayColors)
        binding.selectColorSpinner.adapter = adapter

        binding.selectColorSpinner.onItemSelectedListener = this
        /*binding.selectColorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                TODO("Not yet implemented")
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }*/


        binding.btnRegister.setOnClickListener {
            validateUserData()
        }
    }

    private fun validateUserData() {
        val name = binding.editTextUser.text.toString().trim()
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()

        if (name.isNotBlank() && email.isNotBlank() && password.length >= 3 && colorSelected != null) {
            val preferences = getSharedPreferences(CREDENCIALES, MODE_PRIVATE)
            val edit = preferences.edit()

            val user = User(name = name, email = email, password = password, colorSelected = colorSelected!!)
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

    override fun onItemSelected(
        p0: AdapterView<*>?,
        p1: View?,
        position: Int, //index
        p3: Long
    ) {
        if (arrayColors.get(position) != Colors.NOTHING_SELECTED) {
            colorSelected = arrayColors.get(position)
        } else {
            colorSelected = null
        }
        Toast.makeText(this, "Click item ${arrayColors.get(position)}", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(this, "No selecciono nada", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val CREDENCIALES = "CREDENCIALES"
    }
}
