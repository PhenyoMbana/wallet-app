package com.example.digitalwallet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digitalwallet.R
import com.example.digitalwallet.adapters.ExchangeRateAdapter
import com.example.digitalwallet.databinding.FragmentCurrenciesBinding
import com.example.digitalwallet.models.Currency
import com.example.digitalwallet.models.ExchangeRate
import java.text.DecimalFormat

class CurrenciesFragment : Fragment() {

    private var _binding: FragmentCurrenciesBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var exchangeRateAdapter: ExchangeRateAdapter
    
    // Mock exchange rates
    private val exchangeRates = mapOf(
        "ZAR" to mapOf("USD" to 0.054, "EUR" to 0.05, "GBP" to 0.043, "JPY" to 8.18, "ZAR" to 1.0),
        "USD" to mapOf("ZAR" to 18.52, "EUR" to 0.92, "GBP" to 0.79, "JPY" to 151.45, "USD" to 1.0),
        "EUR" to mapOf("ZAR" to 20.13, "USD" to 1.09, "GBP" to 0.86, "JPY" to 164.62, "EUR" to 1.0),
        "GBP" to mapOf("ZAR" to 23.41, "USD" to 1.27, "EUR" to 1.16, "JPY" to 191.42, "GBP" to 1.0),
        "JPY" to mapOf("ZAR" to 0.122, "USD" to 0.0066, "EUR" to 0.0061, "GBP" to 0.0052, "JPY" to 1.0)
    )
    
    // List of currencies
    private val currencies = listOf(
        Currency("ZAR", "South African Rand", "R", R.drawable.ic_currency_zar),
        Currency("USD", "US Dollar", "$", R.drawable.ic_currency_usd),
        Currency("EUR", "Euro", "€", R.drawable.ic_currency_eur),
        Currency("GBP", "British Pound", "£", R.drawable.ic_currency_gbp),
        Currency("JPY", "Japanese Yen", "¥", R.drawable.ic_currency_jpy)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrenciesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupBaseCurrency()
        setupTabLayout()
        setupCurrencyConverter()
        setupExchangeRates("ZAR")
    }
    
    private fun setupBaseCurrency() {
        // Set up base currency spinner
        val currencyCodes = currencies.map { "${it.code} (${it.symbol})" }.toTypedArray()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, currencyCodes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        
        binding.spinnerBaseCurrency.adapter = adapter
        binding.spinnerBaseCurrency.setSelection(0) // Default to ZAR
        
        binding.spinnerBaseCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCurrency = currencies[position].code
                setupExchangeRates(selectedCurrency)
            }
            
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    
    private fun setupTabLayout() {
        // Set up tabs
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Converter"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Exchange Rates"))
        
        // Handle tab selection
        binding.tabLayout.addOnTabSelectedListener(object : com.google.android.material.tabs.TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: com.google.android.material.tabs.TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        binding.cardConverter.visibility = View.VISIBLE
                        binding.cardExchangeRates.visibility = View.GONE
                    }
                    1 -> {
                        binding.cardConverter.visibility = View.GONE
                        binding.cardExchangeRates.visibility = View.VISIBLE
                    }
                }
            }
            
            override fun onTabUnselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
            
            override fun onTabReselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
        })
    }
    
    private fun setupCurrencyConverter() {
        // Set up from currency spinner
        val fromAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, currencies.map { it.code })
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerFromCurrency.adapter = fromAdapter
        binding.spinnerFromCurrency.setSelection(0) // Default to ZAR
        
        // Set up to currency spinner
        val toAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, currencies.map { it.code })
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerToCurrency.adapter = toAdapter
        binding.spinnerToCurrency.setSelection(1) // Default to USD
        
        // Set up conversion button
        binding.buttonConvert.setOnClickListener {
            convertCurrency()
        }
        
        // Set up swap button
        binding.buttonSwap.setOnClickListener {
            val fromPosition = binding.spinnerFromCurrency.selectedItemPosition
            val toPosition = binding.spinnerToCurrency.selectedItemPosition
            
            binding.spinnerFromCurrency.setSelection(toPosition)
            binding.spinnerToCurrency.setSelection(fromPosition)
            
            convertCurrency()
        }
        
        // Initial conversion
        convertCurrency()
    }
    
    private fun convertCurrency() {
        val amount = binding.editAmount.text.toString().toDoubleOrNull() ?: 1.0
        val fromCurrency = currencies[binding.spinnerFromCurrency.selectedItemPosition].code
        val toCurrency = currencies[binding.spinnerToCurrency.selectedItemPosition].code
        
        val rate = exchangeRates[fromCurrency]?.get(toCurrency) ?: 1.0
        val result = amount * rate
        
        val fromSymbol = currencies.find { it.code == fromCurrency }?.symbol ?: ""
        val toSymbol = currencies.find { it.code == toCurrency }?.symbol ?: ""
        
        val formatter = DecimalFormat("#,##0.00")
        
        binding.textConversionResult.text = "$toSymbol${formatter.format(result)}"
        binding.textConversionRate.text = "1 $fromCurrency = $toSymbol${formatter.format(rate)} $toCurrency"
    }
    
    private fun setupExchangeRates(baseCurrency: String) {
        val rates = mutableListOf<ExchangeRate>()
        
        // Get exchange rates for the selected base currency
        exchangeRates[baseCurrency]?.forEach { (currency, rate) ->
            if (currency != baseCurrency) {
                val currencyInfo = currencies.find { it.code == currency }
                if (currencyInfo != null) {
                    rates.add(ExchangeRate(currencyInfo, rate))
                }
            }
        }
        
        // Set up adapter
        exchangeRateAdapter = ExchangeRateAdapter(rates, baseCurrency)
        binding.recyclerExchangeRates.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = exchangeRateAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

