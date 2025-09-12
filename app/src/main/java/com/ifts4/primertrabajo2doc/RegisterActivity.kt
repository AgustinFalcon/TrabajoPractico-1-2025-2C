package com.ifts4.primertrabajo2doc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ifts4.primertrabajo2doc.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("userName")
        val age = intent.getIntExtra("userAge", 0)

        //binding.textViewWelcome.text = "Hola $name, $age"
    }
}