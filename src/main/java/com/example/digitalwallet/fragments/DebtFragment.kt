package com.example.digitalwallet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digitalwallet.adapters.DebtAdapter
import com.example.digitalwallet.databinding.FragmentDebtBinding
import com.example.digitalwallet.models.Debt
import com.example.digitalwallet.utils.formatCurrency
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DebtFragment : Fragment() {

    private var _binding: FragmentDebtBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var debtAdapter: DebtAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDebtBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupDebts()
        setupTabLayout()
    }
    
    private fun setupDebts() {
        // Mock data for debts
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val debts = listOf(
            Debt(
                1,
                "Student Loan",
                15000.0,
                8500.0,
                4.5,
                150.0,
                dateFormat.parse("2024-04-15") ?: Date()
            ),
            Debt(
                2,
                "Credit Card",
                3000.0,
                1200.0,
                18.99,
                50.0,
                dateFormat.parse("2024-04-05") ?: Date()
            ),
            Debt(
                3,
                "Car Loan",
                20000.0,
                12000.0,
                3.9,
                350.0,
                dateFormat.parse("2024-04-20") ?: Date()
            )
        )
        
        // Calculate total debt
        val totalDebt = debts.sumOf { it.remainingAmount }
        val totalOriginalDebt = debts.sumOf { it.totalAmount }
        val totalProgress = ((totalOriginalDebt - totalDebt) / totalOriginalDebt) * 100
        
        // Calculate total monthly payment
        val totalMonthlyPayment = debts.sumOf { it.minimumPayment }
        
        // Update UI
        binding.textTotalDebt.text = formatCurrency(totalDebt)
        binding.progressTotal.progress = totalProgress.toInt()
        binding.textTotalProgress.text = "${totalProgress.toInt()}% paid off"
        
        binding.textMonthlyPayment.text = formatCurrency(totalMonthlyPayment)
        
        // Set up adapter
        debtAdapter = DebtAdapter(debts)
        binding.recyclerDebts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = debtAdapter
        }
    }
    
    private fun setupTabLayout() {
        // Set up tabs
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("All Debts"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Add New"))
        
        // Handle tab selection
        binding.tabLayout.addOnTabSelectedListener(object : com.google.android.material.tabs.TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: com.google.android.material.tabs.TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        binding.recyclerDebts.visibility = View.VISIBLE
                        binding.cardAddDebt.visibility = View.GONE
                    }
                    1 -> {
                        binding.recyclerDebts.visibility = View.GONE
                        binding.cardAddDebt.visibility = View.VISIBLE
                    }
                }
            }
            
            override fun onTabUnselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
            
            override fun onTabReselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

