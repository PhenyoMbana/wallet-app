package com.example.digitalwallet.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalwallet.databinding.ItemCategoryBinding
import com.example.digitalwallet.models.Category
import com.example.digitalwallet.utils.formatCurrency

class CategoryAdapter(private val categories: List<Category>) : 
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount() = categories.size

    class CategoryViewHolder(private val binding: ItemCategoryBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(category: Category) {
            binding.textCategoryName.text = category.name
            binding.textCategoryBudget.text = "Budget: ${formatCurrency(category.budget)}"
            
            try {
                binding.viewCategoryColor.setBackgroundColor(Color.parseColor(category.color))
            } catch (e: Exception) {
                // Use default color if parsing fails
                binding.viewCategoryColor.setBackgroundColor(Color.GRAY)
            }
        }
    }
}

