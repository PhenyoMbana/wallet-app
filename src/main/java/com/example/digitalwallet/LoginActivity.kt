package com.example.digitalwallet

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.digitalwallet.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var showPassword = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
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

        // Register link
        binding.textRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        // Login button
        binding.buttonLogin.setOnClickListener {
            // In a real app, you would validate inputs and authenticate the user
            if (validateInputs()) {
                // Simulate successful login
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        // Forgot password
        binding.textForgotPassword.setOnClickListener {
            // Handle forgot password
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
        }

        return isValid
    }
}

