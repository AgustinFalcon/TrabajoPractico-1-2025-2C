package com.ifts4.primertrabajo2doc.fragments.third

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ifts4.primertrabajo2doc.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

    private val viewModel by viewModels<ThirdViewModel>()

    private lateinit var binding: FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val viewModel = ViewModelProvider(this).get(ThirdViewModel::class.java)

        binding.etEmail.addTextChangedListener { email ->
            viewModel.validateEmail(email = email.toString())
        }

        binding.etPassword.addTextChangedListener { password ->
            viewModel.validatePassword(password = password.toString())
        }

        binding.btnNext.setOnClickListener {
            Toast.makeText(requireContext(), "button clicked", Toast.LENGTH_SHORT).show()
        }

        // Observer de estados (Patron observer)
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ThirdStates.SuccessEmail -> {
                    binding.layoutEmail.error = null
                }

                is ThirdStates.ErrorEmail -> {
                    binding.layoutEmail.error = "formato email incorrecto"
                }

                is ThirdStates.SuccessPassword -> {
                    binding.layoutPassword.error = null
                }

                is ThirdStates.ErrorPassword -> {
                    binding.layoutPassword.error = "Minimo: ${state.password.length}/4"
                }

                is ThirdStates.SuccessButton -> {
                    binding.btnNext.isEnabled = true
                }

                is ThirdStates.ErrorButton -> {
                    binding.btnNext.isEnabled = false
                }
            }
        }
    }

}