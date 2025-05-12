package com.example.digitalwallet.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalwallet.databinding.ItemExchangeRateBinding
import com.example.digitalwallet.models.ExchangeRate
import java.text.DecimalFormat

class ExchangeRateAdapter(
    private val exchangeRates: List<ExchangeRate>,
    private val baseCurrency: String
) : RecyclerView.Adapter<ExchangeRateAdapter.ExchangeRateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeRateViewHolder {
        val binding = ItemExchangeRateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExchangeRateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExchangeRateViewHolder, position: Int) {
        holder.bind(exchangeRates[position], baseCurrency)
    }

    override fun getItemCount() = exchangeRates.size

    class ExchangeRateViewHolder(private val binding: ItemExchangeRateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(exchangeRate: ExchangeRate, baseCurrency: String) {
            val currency = exchangeRate.currency
            
            binding.textCurrencyCode.text = currency.code
            binding.textCurrencyName.text = currency.name
            binding.imageCurrencyIcon.setImageResource(currency.iconResId)
            
            val formatter = DecimalFormat("#,##0.0000")
            binding.textExchangeRate.text = formatter.format(exchangeRate.rate)
            binding.textBaseLabel.text = "1 $baseCurrency ="
        }
    }
}

