package com.example.digitalwallet.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalwallet.R
import com.example.digitalwallet.databinding.ItemMenuBinding
import com.example.digitalwallet.models.MenuItem

class MenuItemAdapter(
    private val menuItems: List<MenuItem>,
    private val onItemClick: (MenuItem) -> Unit
) : RecyclerView.Adapter<MenuItemAdapter.MenuItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val binding = ItemMenuBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MenuItemViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        holder.bind(menuItems[position])
    }

    override fun getItemCount() = menuItems.size

    class MenuItemViewHolder(
        private val binding: ItemMenuBinding,
        private val onItemClick: (MenuItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(menuItem: MenuItem) {
            binding.textMenuTitle.text = menuItem.title
            
            // Set icon
            val context = binding.root.context
            val iconResId = when (menuItem.iconResName) {
                "ic_package" -> R.drawable.ic_package
                "ic_bar_chart" -> R.drawable.ic_bar_chart
                "ic_credit_card" -> R.drawable.ic_credit_card
                "ic_globe" -> R.drawable.ic_globe
                "ic_settings" -> R.drawable.ic_settings
                else -> R.drawable.ic_settings // Default icon
            }
            
            binding.imageMenuIcon.setImageResource(iconResId)
            
            // Set background color
            try {
                binding.cardMenuIcon.setCardBackgroundColor(Color.parseColor(menuItem.colorHex + "20"))
                binding.imageMenuIcon.setColorFilter(Color.parseColor(menuItem.colorHex))
            } catch (e: Exception) {
                // Fallback color
                binding.cardMenuIcon.setCardBackgroundColor(Color.GRAY)
            }
            
            // Set click listener
            binding.root.setOnClickListener {
                onItemClick(menuItem)
            }
        }
    }
}

