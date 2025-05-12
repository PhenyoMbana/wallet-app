package com.example.digitalwallet.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalwallet.R
import com.example.digitalwallet.databinding.ItemExpenseBinding
import com.example.digitalwallet.models.Expense
import com.example.digitalwallet.utils.formatCurrency
import java.text.SimpleDateFormat
import java.util.Locale

class ExpenseAdapter(private val expenses: List<Expense>) : 
    RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = ItemExpenseBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(expenses[position])
    }

    override fun getItemCount() = expenses.size

    class ExpenseViewHolder(private val binding: ItemExpenseBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(expense: Expense) {
            binding.textExpenseTitle.text = expense.title
            binding.textExpenseAmount.text = formatCurrency(expense.amount)
            binding.textExpenseCategory.text = expense.category
            
            // Format date
            val dateFormat = SimpleDateFormat("MMM d", Locale.getDefault())
            binding.textExpenseDate.text = dateFormat.format(expense.date)
            
            // Set icon based on category
            val iconResId = when (expense.category) {
                "Groceries" -> R.drawable.ic_shopping_bag
                "Housing" -> R.drawable.ic_home
                "Food" -> R.drawable.ic_utensils
                "Transportation" -> R.drawable.ic_car
                "Entertainment" -> R.drawable.ic_film
                else -> R.drawable.ic_credit_card
            }
            binding.imageExpenseIcon.setImageResource(iconResId)
        }
    }
}

