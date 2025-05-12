package com.example.digitalwallet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digitalwallet.adapters.ExpenseAdapter
import com.example.digitalwallet.databinding.FragmentDashboardBinding
import com.example.digitalwallet.models.Expense
import com.example.digitalwallet.utils.formatCurrency
import java.util.Date

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var expenseAdapter: ExpenseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupBudgetOverview()
        setupRecentExpenses()
        setupButtons()
    }
    
    private fun setupBudgetOverview() {
        // Mock data
        val totalBudget = 2000.0
        val totalSpent = 1250.0
        val percentSpent = (totalSpent / totalBudget) * 100
        
        binding.textBudgetAmount.text = formatCurrency(totalBudget)
        binding.textSpentAmount.text = formatCurrency(totalSpent)
        binding.progressBudget.progress = percentSpent.toInt()
        binding.textBudgetProgress.text = "${percentSpent.toInt()}% spent"
        binding.textBudgetRemaining.text = "R${(totalBudget - totalSpent).toInt()} remaining"
    }
    
    private fun setupRecentExpenses() {
        // Mock data
        val expenses = listOf(
            Expense(1, "Grocery Shopping", 85.75, Date(), "Groceries"),
            Expense(2, "Rent Payment", 1200.0, Date(), "Housing"),
            Expense(3, "Dinner at Restaurant", 45.8, Date(), "Food"),
            Expense(4, "Gas Station", 35.5, Date(), "Transportation"),
            Expense(5, "Movie Tickets", 24.0, Date(), "Entertainment")
        )
        
        expenseAdapter = ExpenseAdapter(expenses)
        binding.recyclerRecentExpenses.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = expenseAdapter
        }
    }
    
    private fun setupButtons() {
        binding.buttonAddExpense.setOnClickListener {
            // Navigate to add expense screen
        }
        
        binding.buttonSetBudget.setOnClickListener {
            // Navigate to budget screen
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

