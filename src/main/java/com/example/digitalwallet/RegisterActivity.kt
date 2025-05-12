package com.example.digitalwallet

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.digitalwallet.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private var showPassword = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        // Back button
        binding.buttonBack.setOnClickListener {
            finish()
        }

        // Toggle password visibility
        binding.buttonTogglePassword.setOnClickListener {
            showPassword = !showPassword
            togglePasswordVisibility()
        }

        // Login link
        binding.textLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Register button
        binding.buttonRegister.setOnClickListener {
            // In a real app, you would validate inputs and register the user
            if (validateInputs()) {
                // Simulate successful registration
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun togglePasswordVisibility() {
        if (showPassword) {
            binding.editPassword.transformationMethod = null
            binding.imageEye.setImageResource(R.drawable.ic_eye_off)
        } else {
            binding.editPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.imageEye.setImageResource(R.drawable.ic_eye)
        }
        // Maintain cursor position
        binding.editPassword.setSelection(binding.editPassword.text.length)
    }

    private fun validateInputs(): Boolean {
        var isValid = true

        // Name validation
        val name = binding.editName.text.toString().trim()
        if (name.isEmpty()) {
            binding.editName.error = "Name is required"
            isValid = false
        }

        // Email validation
        val email = binding.editEmail.text.toString().trim()
        if (email.isEmpty()) {
            binding.editEmail.error = "Email is required"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editEmail.error = "Enter a valid email"
            isValid = false
        }

        // Password validation
        val password = binding.editPassword.text.toString()
        if (password.isEmpty()) {
            binding.editPassword.error = "Password is required"
            isValid = false
        } else if (password.length < 8) {
            binding.editPassword.error = "Password must be at least 8 characters"
            isValid = false
        }

        return isValid
    }
}

