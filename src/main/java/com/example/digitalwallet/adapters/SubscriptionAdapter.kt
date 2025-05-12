package com.example.digitalwallet.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalwallet.databinding.ItemSubscriptionBinding
import com.example.digitalwallet.models.Subscription
import com.example.digitalwallet.utils.formatCurrency
import java.text.SimpleDateFormat
import java.util.Locale

class SubscriptionAdapter(private val subscriptions: List<Subscription>) : 
    RecyclerView.Adapter<SubscriptionAdapter.SubscriptionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionViewHolder {
        val binding = ItemSubscriptionBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return SubscriptionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubscriptionViewHolder, position: Int) {
        holder.bind(subscriptions[position])
    }

    override fun getItemCount() = subscriptions.size

    class SubscriptionViewHolder(private val binding: ItemSubscriptionBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(subscription: Subscription) {
            binding.textSubscriptionName.text = subscription.name
            binding.textSubscriptionAmount.text = formatCurrency(subscription.amount)
            binding.textSubscriptionCycle.text = subscription.cycle
            
            // Format date
            val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
            binding.textNextPayment.text = "Next: ${dateFormat.format(subscription.nextPayment)}"
            
            // Set icon
            val context = binding.root.context
            val iconResId = context.resources.getIdentifier(
                subscription.iconName,
                "drawable",
                context.packageName
            )
            
            if (iconResId != 0) {
                binding.imageSubscriptionIcon.setImageResource(iconResId)
            }
            
            // Set background color
            try {
                binding.cardSubscriptionIcon.setCardBackgroundColor(Color.parseColor(subscription.colorHex + "20"))
                binding.imageSubscriptionIcon.setColorFilter(Color.parseColor(subscription.colorHex))
            } catch (e: Exception) {
                // Fallback color
                binding.cardSubscriptionIcon.setCardBackgroundColor(Color.LTGRAY)
            }
        }
    }
}

