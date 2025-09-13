package com.ifts4.primertrabajo2doc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ifts4.primertrabajo2doc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextAge = findViewById<EditText>(R.id.editTextAge)
        val butonLogin = findViewById<Button>(R.id.butonLogin)

        //butonLogin.setOnClickListener{}
        binding.butonLogin.setOnClickListener {
            val name = editTextName.text.toString()
            val age = editTextAge.text.toString()

            validateData()
        }
    }


    fun validateData() {
        val nameBinding = binding.editTextName.text.toString()
        val ageBinding = binding.editTextAge.text.toString()

        val preferences = getSharedPreferences(RegisterActivity.CREDENCIALES, MODE_PRIVATE)
        val edit = preferences.edit()

        val nameSharedPref = preferences.getString("userName", "")
        val passSharedPref = preferences.getString("password", "")

        if (nameBinding == nameSharedPref && ageBinding == passSharedPref) {
            // mensaje bienvenido
            //Toast.makeText(this, "Bienvenido/a $nameBinding", Toast.LENGTH_LONG).show()
            //val intent = Intent(this, RegisterActivity::class.java)
            //intent.putExtra("userName", nameBinding)
            //intent.putExtra("userAge", ageBinding.toInt())
            //startActivity(intent)
            Toast.makeText(this, "CORRECTO!", Toast.LENGTH_LONG).show()

        } else {
            // mensaje complete todos los campos
            Toast.makeText(this, "Valores incorrectos!", Toast.LENGTH_LONG).show()
        }
    }

}
