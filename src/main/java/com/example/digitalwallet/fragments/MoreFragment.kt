package com.example.digitalwallet.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.digitalwallet.adapters.MenuItemAdapter
import com.example.digitalwallet.databinding.FragmentMoreBinding
import com.example.digitalwallet.models.MenuItem

class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var menuItemAdapter: MenuItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupMenuItems()
    }
    
    private fun setupMenuItems() {
        // Create menu items
        val menuItems = listOf(
            MenuItem(
                "Categories",
                "com.example.digitalwallet.R.drawable.ic_package",
                "#2196F3",
                "categories"
            ),
            MenuItem(
                "Reports",
                "com.example.digitalwallet.R.drawable.ic_bar_chart",
                "#9C27B0",
                "reports"
            ),
            MenuItem(
                "Subscriptions",
                "com.example.digitalwallet.R.drawable.ic_credit_card",
                "#E91E63",
                "subscriptions"
            ),
            MenuItem(
                "Debt Tracker",
                "com.example.digitalwallet.R.drawable.ic_credit_card",
                "#F44336",
                "debt"
            ),
            MenuItem(
                "Currencies",
                "com.example.digitalwallet.R.drawable.ic_globe",
                "#4CAF50",
                "currencies"
            ),
            MenuItem(
                "Settings",
                "com.example.digitalwallet.R.drawable.ic_settings",
                "#757575",
                "settings"
            )
        )
        
        // Set up adapter with click listener
        menuItemAdapter = MenuItemAdapter(menuItems) { menuItem ->
            // Handle menu item click
            when (menuItem.route) {
                "categories" -> navigateToFragment(CategoriesFragment())
                "reports" -> navigateToFragment(ReportsFragment())
                "subscriptions" -> navigateToFragment(SubscriptionsFragment())
                "debt" -> navigateToFragment(DebtFragment())
                "currencies" -> navigateToFragment(CurrenciesFragment())
                "settings" -> navigateToFragment(SettingsFragment())
            }
        }
        
        // Set up RecyclerView
        binding.recyclerMenuItems.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = menuItemAdapter
        }
    }
    
    private fun navigateToFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(com.example.digitalwallet.R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

