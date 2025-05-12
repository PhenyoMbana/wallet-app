package com.example.digitalwallet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digitalwallet.adapters.SubscriptionAdapter
import com.example.digitalwallet.databinding.FragmentSubscriptionsBinding
import com.example.digitalwallet.models.Subscription
import com.example.digitalwallet.utils.formatCurrency
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SubscriptionsFragment : Fragment() {

    private var _binding: FragmentSubscriptionsBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var subscriptionAdapter: SubscriptionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubscriptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupSubscriptions()
        setupTabLayout()
    }
    
    private fun setupSubscriptions() {
        // Mock data for subscriptions
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val subscriptions = listOf(
            Subscription(
                1,
                "Netflix",
                15.99,
                "Monthly",
                dateFormat.parse("2024-04-15") ?: Date(),
                "ic_tv",
                "#F44336"
            ),
            Subscription(
                2,
                "Spotify",
                9.99,
                "Monthly",
                dateFormat.parse("2024-04-10") ?: Date(),
                "ic_music",
                "#4CAF50"
            ),
            Subscription(
                3,
                "iCloud",
                2.99,
                "Monthly",
                dateFormat.parse("2024-04-05") ?: Date(),
                "ic_cloud",
                "#2196F3"
            ),
            Subscription(
                4,
                "Medium",
                5.0,
                "Monthly",
                dateFormat.parse("2024-04-22") ?: Date(),
                "ic_book",
                "#FFC107"
            )
        )
        
        // Calculate total monthly cost
        val totalMonthlyCost = subscriptions.sumOf { it.amount }
        binding.textTotalAmount.text = formatCurrency(totalMonthlyCost)
        binding.textSubscriptionCount.text = "${subscriptions.size} active"
        
        // Set up adapter
        subscriptionAdapter = SubscriptionAdapter(subscriptions)
        binding.recyclerSubscriptions.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = subscriptionAdapter
        }
    }
    
    private fun setupTabLayout() {
        // Set up tabs
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Active"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Add New"))
        
        // Handle tab selection
        binding.tabLayout.addOnTabSelectedListener(object : com.google.android.material.tabs.TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: com.google.android.material.tabs.TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        binding.recyclerSubscriptions.visibility = View.VISIBLE
                        binding.cardAddSubscription.visibility = View.GONE
                    }
                    1 -> {
                        binding.recyclerSubscriptions.visibility = View.GONE
                        binding.cardAddSubscription.visibility = View.VISIBLE
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

