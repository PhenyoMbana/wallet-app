package com.example.digitalwallet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.digitalwallet.databinding.FragmentBudgetBinding
import com.example.digitalwallet.utils.formatCurrency

class BudgetFragment : Fragment() {

    private var _binding: FragmentBudgetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBudgetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupBudgetOverview()
        setupCategoryBudgets()
    }
    
    private fun setupBudgetOverview() {
        // Mock data
        val totalBudget = 3000.0
        val totalSpent = 1250.0
        val percentSpent = (totalSpent / totalBudget) * 100
        
        binding.textTotalBudget.text = formatCurrency(totalBudget)
        binding.textTotalSpent.text = formatCurrency(totalSpent)
        binding.progressTotal.progress = percentSpent.toInt()
        binding.textTotalPercentage.text = "${percentSpent.toInt()}% spent"
    }
    
    private fun setupCategoryBudgets() {
        // This would be implemented with a RecyclerView in a real app
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

