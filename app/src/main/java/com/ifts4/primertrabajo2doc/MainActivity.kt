package com.ifts4.primertrabajo2doc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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


        butonLogin.setOnClickListener {
            val name = editTextName.text.toString()
            val age = editTextAge.text.toString()

            validateData()

        }
    }


    fun validateData() {
        val nameBinding = binding.editTextName.text.toString()
        val ageBinding = binding.editTextAge.text.toString()

        if (nameBinding.isNotEmpty() && ageBinding.isNotEmpty()) {
            // mensaje bienvenido
            //Toast.makeText(this, "Bienvenido/a $nameBinding", Toast.LENGTH_LONG).show()
            val intent = Intent(this, RegisterActivity::class.java)
            intent.putExtra("userName", nameBinding)
            intent.putExtra("userAge", ageBinding.toInt())
            startActivity(intent)

        } else {
            // mensaje complete todos los campos
            Toast.makeText(this, "Complete todos los campos!", Toast.LENGTH_LONG).show()
        }
    }

}
