package com.example.digitalwallet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.digitalwallet.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    
    // Mock user data
    private val userData = mapOf(
        "name" to "John Doe",
        "email" to "john@example.com",
        "currency" to "ZAR",
        "darkMode" to true,
        "notifications" to true
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUserData()
        setupCurrencySpinner()
        setupSwitches()
        setupButtons()
    }
    
    private fun setupUserData() {
        binding.editName.setText(userData["name"] as String)
        binding.editEmail.setText(userData["email"] as String)
    }
    
    private fun setupCurrencySpinner() {
        val currencies = resources.getStringArray(com.example.digitalwallet.R.array.currencies)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        
        binding.spinnerCurrency.adapter = adapter
        
        // Set selected currency
        val currencyIndex = currencies.indexOfFirst { it.startsWith(userData["currency"] as String) }
        if (currencyIndex != -1) {
            binding.spinnerCurrency.setSelection(currencyIndex)
        }
        
        binding.spinnerCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Handle currency selection
            }
            
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    
    private fun setupSwitches() {
        binding.switchDarkMode.isChecked = userData["darkMode"] as Boolean
        binding.switchNotifications.isChecked = userData["notifications"] as Boolean
        
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            // Handle dark mode toggle
        }
        
        binding.switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            // Handle notifications toggle
        }
    }
    
    private fun setupButtons() {
        binding.buttonSave.setOnClickListener {
            // Validate and save user data
            if (validateInputs()) {
                Toast.makeText(requireContext(), "Settings saved", Toast.LENGTH_SHORT).show()
            }
        }
        
        binding.buttonChangePassword.setOnClickListener {
            // Handle change password
            Toast.makeText(requireContext(), "Change password feature coming soon", Toast.LENGTH_SHORT).show()
        }
        
        binding.buttonEnableTwoFactor.setOnClickListener {
            // Handle enable two-factor authentication
            Toast.makeText(requireContext(), "Two-factor authentication coming soon", Toast.LENGTH_SHORT).show()
        }
        
        binding.buttonExportData.setOnClickListener {
            // Handle export data
            Toast.makeText(requireContext(), "Export data feature coming soon", Toast.LENGTH_SHORT).show()
        }
        
        binding.buttonDeleteExpenses.setOnClickListener {
            // Handle delete all expenses
            Toast.makeText(requireContext(), "Delete expenses feature coming soon", Toast.LENGTH_SHORT).show()
        }
        
        binding.buttonDeleteAccount.setOnClickListener {
            // Handle delete account
            Toast.makeText(requireContext(), "Delete account feature coming soon", Toast.LENGTH_SHORT).show()
        }
        
        binding.buttonSignOut.setOnClickListener {
            // Handle sign out
            Toast.makeText(requireContext(), "Signing out...", Toast.LENGTH_SHORT).show()
            // In a real app, you would navigate to the login screen
        }
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
        
        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

