package com.example.digitalwallet.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalwallet.databinding.ItemDebtBinding
import com.example.digitalwallet.models.Debt
import com.example.digitalwallet.utils.formatCurrency
import java.text.SimpleDateFormat
import java.util.Locale

class DebtAdapter(private val debts: List<Debt>) : 
    RecyclerView.Adapter<DebtAdapter.DebtViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebtViewHolder {
        val binding = ItemDebtBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return DebtViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DebtViewHolder, position: Int) {
        holder.bind(debts[position])
    }

    override fun getItemCount() = debts.size

    class DebtViewHolder(private val binding: ItemDebtBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(debt: Debt) {
            binding.textDebtName.text = debt.name
            binding.textRemainingAmount.text = formatCurrency(debt.remainingAmount)
            binding.textInterestRate.text = "${debt.interestRate}%"
            binding.textMonthlyPayment.text = formatCurrency(debt.minimumPayment)
            
            // Format date
            val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
            binding.textDueDate.text = dateFormat.format(debt.dueDate)
            
            // Calculate progress
            val progressPercentage = ((debt.totalAmount - debt.remainingAmount) / debt.totalAmount) * 100
            binding.progressDebt.progress = progressPercentage.toInt()
            binding.textRemainingLabel.text = "${formatCurrency(debt.remainingAmount)} left"
            binding.textProgressPercentage.text = "${progressPercentage.toInt()}% paid"
        }
    }
}

